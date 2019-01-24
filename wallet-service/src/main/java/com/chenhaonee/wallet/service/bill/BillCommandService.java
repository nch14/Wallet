package com.chenhaonee.wallet.service.bill;

import com.chenhaonee.wallet.domain.Bill;
import com.chenhaonee.wallet.domain.enums.BillType;
import com.chenhaonee.wallet.remote.request.CreateBillRequest;
import com.chenhaonee.wallet.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * Created by chenhaonee at 2019/1/25
 */
@Service
public class BillCommandService {

    @Autowired
    private BillRepository billRepository;


    public Long addBill(long userId, CreateBillRequest request){
        Bill bill = new Bill();
        bill.setUserId(userId);
        bill.setMoney((int)(request.money*100));
        bill.setBillType(Optional.ofNullable(request.billType).orElse(BillType.OTHERS));
        bill.setCreateTime(Optional.ofNullable(request.createTime).orElse(new Date()));
        bill.setDueTime(Optional.ofNullable(request.dueTime).orElse(new Date()));
        bill.setMojo(request.mojo);

        bill = billRepository.save(bill);
        return bill.getId();
    }

}
