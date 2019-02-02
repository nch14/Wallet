package cn.chenhaonee.wallet.service.bill;

import cn.chenhaonee.wallet.domain.Bill;
import cn.chenhaonee.wallet.remote.request.CreateBillRequest;
import cn.chenhaonee.wallet.repository.BillRepository;
import cn.chenhaonee.wallet.mapper.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenhaonee at 2019/1/25
 */
@Service
public class BillCommandService {

    @Autowired
    private BillRepository billRepository;

    public Long addBill(long userId, CreateBillRequest request) {
        Bill bill = BillMapper.toBill(userId, request);
        bill = billRepository.save(bill);
        return bill.getId();
    }

}
