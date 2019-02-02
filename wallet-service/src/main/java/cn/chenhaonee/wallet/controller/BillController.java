package cn.chenhaonee.wallet.controller;

import cn.chenhaonee.wallet.remote.request.CreateBillRequest;
import cn.chenhaonee.wallet.annotations.RequireAuth;
import cn.chenhaonee.wallet.annotations.UserId;
import cn.chenhaonee.wallet.remote.response.Response;
import cn.chenhaonee.wallet.service.bill.BillCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenhaonee at 2019/1/25
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/bill")
public class BillController {

    @Autowired
    private BillCommandService commandService;

    @PutMapping
    @RequireAuth
    public Response doBill(@RequestBody CreateBillRequest request, @UserId Long userId){
       Long billId = commandService.addBill(userId,request);
       return Response.success(billId);
    }

}
