package cn.chenhaonee.wallet.service.expense;

import cn.chenhaonee.wallet.domain.Bill;
import cn.chenhaonee.wallet.domain.enums.BillType;
import cn.chenhaonee.wallet.remote.response.HalfYearExpense;
import cn.chenhaonee.wallet.remote.response.MonthDailyExpense;
import cn.chenhaonee.wallet.remote.response.MonthTypedExpense;
import cn.chenhaonee.wallet.utils.Times;
import cn.chenhaonee.wallet.service.bill.BillQueryService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by chenhaonee at 2019/2/1
 */
@Service
public class ExpenseQueryService {

    @Autowired
    private BillQueryService billQueryService;

    public MonthTypedExpense getMonthTypedExpense(Long userId) {
        Date[] dates = Times.monthRange();
        List<Bill> bills = billQueryService.lookup(userId, dates[0], dates[1]);

        Map<BillType, MonthTypedExpense.TypedExpense> map = Arrays.stream(BillType.values())
                .collect(Collectors.toMap(type -> type, type -> new MonthTypedExpense.TypedExpense(type.memo, 0)));

        bills.parallelStream().forEach(bill -> {
            MonthTypedExpense.TypedExpense typedExpense = map.get(bill.getBillType());
            double money = typedExpense.getMoney() + bill.getMoney();
            typedExpense.setMoney(money);
        });

        List<MonthTypedExpense.TypedExpense> expenses = map.values().parallelStream()
                .peek(typedExpense -> {
                    typedExpense.setMoney(typedExpense.getMoney() / 100);
                })
                .collect(Collectors.toList());
        return new MonthTypedExpense(expenses);
    }

    public MonthDailyExpense getMonthDailyExpense(Long userId) {
        Date[] dates = Times.monthRange();
        List<Bill> bills = billQueryService.lookup(userId, dates[0], dates[1]);

        LocalDate current = LocalDate.now();
        int days = current.getDayOfMonth();
        List<MonthDailyExpense.TypedExpense> expenses = Arrays.stream(BillType.values())
                .map(type -> new MonthDailyExpense.TypedExpense(type.memo, new double[days]))
                .collect(Collectors.toList());

        bills.parallelStream().forEach(bill -> {
            expenses.parallelStream().filter(ex -> ex.getType().equals(bill.getBillType().memo))
                    .forEach(ex -> {
                        int day = bill.getCreateTime().getDate() - 1;
                        ex.getMoney()[day] += (double) bill.getMoney() / 100;
                    });
        });

        return new MonthDailyExpense(expenses);
    }

    public HalfYearExpense getHalfYearExpense(Long userId) {
        List<Date[]> ranges = Lists.newArrayList(6,5,4,3,2,1).stream().map(i -> {
            LocalDate now = LocalDate.now();
            now = now.minusMonths(i);
            return Times.monthRange(now.getYear(), now.getMonthValue());
        }).collect(Collectors.toList());

        List<HalfYearExpense.MonthExpense> expenses = ranges.stream().map(range -> {
            List<Bill> bills = billQueryService.lookup(userId, range[0], range[1]);
            int money = bills.stream().map(Bill::getMoney).reduce(Integer::sum).orElse(0);
            double realMoney = (double) money / 100;

            String month = (range[0].getYear() + 1900) + "-" + (range[0].getMonth() + 1);
            return new HalfYearExpense.MonthExpense(month, realMoney);
        }).collect(Collectors.toList());

        return new HalfYearExpense(expenses);
    }
}
