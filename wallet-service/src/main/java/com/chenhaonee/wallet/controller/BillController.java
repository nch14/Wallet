package com.chenhaonee.wallet.controller;

import com.chenhaonee.wallet.remote.request.CreateBillRequest;
import com.chenhaonee.wallet.remote.response.Response;
import com.chenhaonee.wallet.service.bill.BillCommandService;
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

    @PutMapping(value = "/{userId}")
    private Response<Long> doBill(@PathVariable long userId,
                            @RequestBody CreateBillRequest request){
       Long billId = commandService.addBill(userId,request);
       return new Response<>(billId,true);
    }

}
