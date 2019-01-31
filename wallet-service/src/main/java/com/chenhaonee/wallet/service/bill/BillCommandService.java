package com.chenhaonee.wallet.service.bill;

import com.chenhaonee.wallet.domain.Bill;
import com.chenhaonee.wallet.mapper.BillMapper;
import com.chenhaonee.wallet.remote.request.CreateBillRequest;
import com.chenhaonee.wallet.repository.BillRepository;
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
