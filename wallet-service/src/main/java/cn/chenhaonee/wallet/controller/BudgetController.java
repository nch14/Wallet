package cn.chenhaonee.wallet.controller;

import cn.chenhaonee.wallet.annotations.RequireAuth;
import cn.chenhaonee.wallet.annotations.UserId;
import cn.chenhaonee.wallet.service.budget.BudgetQueryService;
import cn.chenhaonee.wallet.remote.response.Dashboard;
import cn.chenhaonee.wallet.remote.response.Response;
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

    @GetMapping(value = "/dashboard")
    @RequireAuth
    public Response dashboard(@UserId Long userId) {
        Dashboard dashboard = queryService.queryDashboard(userId);
        return Response.success(dashboard);
    }
}
