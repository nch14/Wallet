package com.chenhaonee.wallet.controller;

import com.chenhaonee.wallet.remote.response.MonthTypedExpense;
import com.chenhaonee.wallet.remote.response.Response;
import com.chenhaonee.wallet.service.expense.ExpenseQueryService;
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

    @GetMapping
    public Response<MonthTypedExpense> getMonthTypedExpense() {
        MonthTypedExpense monthTypedExpense = queryService.getMonthTypedExpense();
        return new Response<>(monthTypedExpense, true);
    }
}
