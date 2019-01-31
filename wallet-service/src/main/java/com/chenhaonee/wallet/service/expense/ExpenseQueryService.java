package com.chenhaonee.wallet.service.expense;

import com.chenhaonee.wallet.domain.Bill;
import com.chenhaonee.wallet.domain.enums.BillType;
import com.chenhaonee.wallet.remote.response.MonthTypedExpense;
import com.chenhaonee.wallet.service.bill.BillQueryService;
import com.chenhaonee.wallet.utils.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public MonthTypedExpense getMonthTypedExpense() {
        Date[] dates = Times.monthRange();
        List<Bill> bills = billQueryService.lookup(dates[0], dates[1]);

        Map<BillType, MonthTypedExpense.TypedExpense> map = Arrays.stream(BillType.values())
                .collect(Collectors.toMap(type -> type, type -> new MonthTypedExpense.TypedExpense(type.name(), 0)));

        bills.parallelStream().forEach(bill -> {
            MonthTypedExpense.TypedExpense typedExpense = map.get(bill.getBillType());
            double money = typedExpense.getMoney()+bill.getMoney();
            typedExpense.setMoney(money);
        });

        List<MonthTypedExpense.TypedExpense> expenses = map.values().parallelStream().collect(Collectors.toList());
        return new MonthTypedExpense(expenses);
    }
}
