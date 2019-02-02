package cn.chenhaonee.wallet.service.bill;

import cn.chenhaonee.wallet.domain.Bill;
import cn.chenhaonee.wallet.repository.BillRepository;
import cn.chenhaonee.wallet.utils.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by chenhaonee at 2019/1/25
 */
@Service
public class BillQueryService {

    @Autowired
    private BillRepository repository;

    public List<Bill> lookup(Long userId, Date from, Date to) {
        Date fromTime = Times.toZero(from);
        Date toTime = Times.toNight(to);
        return repository.findByUserIdAndCreateTimeBetween(userId, fromTime, toTime);
    }

    public List<Bill> lookup(Long userId, Date date) {
        Date[] range = Times.dayRange(date);
        return repository.findByUserIdAndCreateTimeBetween(userId, range[0], range[1]);
    }

    public List<Bill> lookup(Long userId, String billTime) {
        return repository.findByUserIdAndBillTime(userId, billTime);
    }

}
