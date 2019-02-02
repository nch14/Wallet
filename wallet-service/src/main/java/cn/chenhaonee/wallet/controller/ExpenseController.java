package cn.chenhaonee.wallet.controller;

import cn.chenhaonee.wallet.annotations.RequireAuth;
import cn.chenhaonee.wallet.annotations.UserId;
import cn.chenhaonee.wallet.remote.response.HalfYearExpense;
import cn.chenhaonee.wallet.remote.response.MonthDailyExpense;
import cn.chenhaonee.wallet.remote.response.MonthTypedExpense;
import cn.chenhaonee.wallet.remote.response.Response;
import cn.chenhaonee.wallet.service.expense.ExpenseQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenhaonee at 2019/2/1
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/expense")
public class ExpenseController {

    @Autowired
    private ExpenseQueryService queryService;

    @GetMapping(value = "/month/typed")
    @RequireAuth
    public Response getMonthTypedExpense(@UserId Long userId) {
        MonthTypedExpense monthTypedExpense = queryService.getMonthTypedExpense(userId);
        return Response.success(monthTypedExpense);
    }

    @GetMapping(value = "month/daily")
    @RequireAuth
    public Response getMonthDailyExpense(@UserId Long userId) {
        MonthDailyExpense monthTypedExpense = queryService.getMonthDailyExpense(userId);
        return Response.success(monthTypedExpense);
    }

    @GetMapping(value = "year/half")
    @RequireAuth
    public Response getHalfYearExpense(@UserId Long userId) {
        HalfYearExpense halfYearExpense = queryService.getHalfYearExpense(userId);
        return Response.success(halfYearExpense);
    }
}
