package com.chenhaonee.wallet.service.budget;

import com.chenhaonee.wallet.domain.Bill;
import com.chenhaonee.wallet.service.bill.BillQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by chenhaonee at 2019/2/1
 */
@Service
public class BudgetQueryService {

    @Autowired
    private BillQueryService billQueryService;

    public Double queryTodayLeft(){
       List<Bill> bills =  billQueryService.lookup(new Date());
       double used =(double)bills.parallelStream().map(Bill::getMoney).reduce(Integer::sum).orElse(0)/100;
       return 60 - used;
    }

}
