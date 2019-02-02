package cn.chenhaonee.wallet.mapper;

import cn.chenhaonee.wallet.domain.Bill;
import cn.chenhaonee.wallet.domain.enums.BillType;
import cn.chenhaonee.wallet.domain.enums.PalType;
import cn.chenhaonee.wallet.remote.request.CreateBillRequest;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

/**
 * Created by chenhaonee at 2019/2/1
 */
public class BillMapper {

    public static Bill toBill(long userId, CreateBillRequest request) {
        Bill bill = new Bill();
        bill.setUserId(userId);
        bill.setMoney((int) (request.money * 100));
        bill.setPalType(Optional.ofNullable(request.palType).orElse(PalType.OTHERS));
        bill.setBillType(Optional.ofNullable(request.billType).orElse(BillType.OTHERS));
        bill.setCreateTime(Optional.ofNullable(request.createTime).orElse(new Date()));
        bill.setDueTime(Optional.ofNullable(request.dueTime).orElse(new Date()));
        bill.setMemo(request.memo);
        if (request.daily) {
            String billTime = LocalDate.now().getYear() + "-" + LocalDate.now().getMonthValue() + "-" + LocalDate.now().getDayOfMonth();
            bill.setBillTime(billTime);
        } else {
            String billTime = LocalDate.now().getYear() + "-" + LocalDate.now().getMonthValue();
            bill.setBillTime(billTime);
        }
        return bill;
    }
}
