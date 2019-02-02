package cn.chenhaonee.wallet.controller;

import cn.chenhaonee.wallet.annotations.RequireAuth;
import cn.chenhaonee.wallet.annotations.UserId;
import cn.chenhaonee.wallet.remote.response.Response;
import cn.chenhaonee.wallet.remote.request.CreateIncomeRequest;
import cn.chenhaonee.wallet.service.income.IncomeCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenhaonee at 2019/2/1
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/income")
public class IncomeController {

    @Autowired
    private IncomeCommandService commandService;

    @PutMapping
    @RequireAuth
    public Response addIncome(@RequestBody CreateIncomeRequest request, @UserId Long userId) {
        Long incomeId = commandService.addIncome(userId, request);
        return Response.success(incomeId);
    }
}
