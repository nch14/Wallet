package com.chenhaonee.wallet.controller;

import com.chenhaonee.wallet.remote.response.Response;
import com.chenhaonee.wallet.service.budget.BudgetQueryService;
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
@RequestMapping(value = "/budget")
public class BudgetController {

    @Autowired
    private BudgetQueryService queryService;

    @GetMapping(value = "/today/left")
    public Response<Double> leftForToday() {
        Double left = queryService.queryTodayLeft();
        return new Response<>(left,true);
    }

    @GetMapping(value = "/today/allow")
    public Response<Double> allowForToday() {
        return new Response<>(60.0,true);
    }

}
