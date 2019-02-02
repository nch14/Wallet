package cn.chenhaonee.wallet.controller;

import cn.chenhaonee.wallet.domain.User;
import cn.chenhaonee.wallet.remote.request.CreateUserRequest;
import cn.chenhaonee.wallet.remote.request.LoginRequest;
import cn.chenhaonee.wallet.remote.response.Response;
import cn.chenhaonee.wallet.service.account.AccountCommandService;
import cn.chenhaonee.wallet.service.account.AccountQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenhaonee at 2019/2/2
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountCommandService commandService;

    @Autowired
    private AccountQueryService queryService;

    @PostMapping(value = "/login")
    public Response login(@RequestBody LoginRequest request) {
        return queryService.processLogin(request.username, request.password)
                .map(Response::success)
                .orElse(Response.failure());
    }

    @PutMapping(value = "/join")
    public Response join(@RequestBody CreateUserRequest request) {
        User user = commandService.addAccount(request.username, request.nickname, request.password);
        return Response.success(true);
    }

}
