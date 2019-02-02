package cn.chenhaonee.wallet.service.budget;

import cn.chenhaonee.wallet.domain.Bill;
import cn.chenhaonee.wallet.domain.Income;
import cn.chenhaonee.wallet.remote.response.Dashboard;
import cn.chenhaonee.wallet.service.bill.BillQueryService;
import cn.chenhaonee.wallet.service.income.IncomeQueryService;
import cn.chenhaonee.wallet.utils.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by chenhaonee at 2019/2/1
 */
@Service
public class BudgetQueryService {

    @Autowired
    private BillQueryService billQueryService;

    @Autowired
    private IncomeQueryService incomeQueryService;

    private Double queryTodayLeft(Long userId) {
        String billTime = LocalDate.now().getYear() + "-" + LocalDate.now().getMonthValue() + "-" + LocalDate.now().getDayOfMonth();
        List<Bill> bills = billQueryService.lookup(userId, billTime);
        double used = (double) bills.parallelStream().map(Bill::getMoney).reduce(Integer::sum).orElse(0) / 100;
        return 60 - used;
    }

    public Dashboard queryDashboard(Long userId) {
        double leftForToday = queryTodayLeft(userId);
        double budgetThisMonth = 2000;
        double usedThisMonth = queryMonthUsed(userId);
        double leftThisMonth = budgetThisMonth - usedThisMonth;
        double incomeThisMonth = queryMonthIncome(userId);
        double deposit = 0;

        return new Dashboard(leftForToday, leftThisMonth, budgetThisMonth, usedThisMonth, incomeThisMonth, deposit);
    }

    private Double queryMonthUsed(Long userId) {
        Date[] range = Times.monthRange();
        List<Bill> bills = billQueryService.lookup(userId, range[0], range[1]);
        return (double) bills.parallelStream().map(Bill::getMoney).reduce(Integer::sum).orElse(0) / 100;
    }

    private Double queryMonthIncome(Long userId) {
        String incomeMonth = new SimpleDateFormat("yyyy-MM").format(new Date());
        List<Income> incomes = incomeQueryService.lookup(userId, incomeMonth);
        return (double) incomes.parallelStream().map(Income::getMoney).reduce(Integer::sum).orElse(0) / 100;
    }

}
