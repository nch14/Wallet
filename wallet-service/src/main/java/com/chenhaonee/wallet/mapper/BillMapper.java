package com.chenhaonee.wallet.mapper;

import com.chenhaonee.wallet.domain.Bill;
import com.chenhaonee.wallet.domain.enums.BillType;
import com.chenhaonee.wallet.domain.enums.PalType;
import com.chenhaonee.wallet.remote.request.CreateBillRequest;

import java.util.Date;
import java.util.Optional;

/**
 * Created by chenhaonee at 2019/2/1
 */
public class BillMapper {

    public static Bill toBill(long userId, CreateBillRequest request){
        Bill bill = new Bill();
        bill.setUserId(userId);
        bill.setMoney((int)(request.money*100));
        bill.setPalType(Optional.ofNullable(request.palType).orElse(PalType.OTHERS));
        bill.setBillType(Optional.ofNullable(request.billType).orElse(BillType.OTHERS));
        bill.setCreateTime(Optional.ofNullable(request.createTime).orElse(new Date()));
        bill.setDueTime(Optional.ofNullable(request.dueTime).orElse(new Date()));
        bill.setMemo(request.memo);
        return bill;
    }
}
