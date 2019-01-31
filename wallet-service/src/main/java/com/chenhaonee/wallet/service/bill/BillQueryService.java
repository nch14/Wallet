package com.chenhaonee.wallet.service.bill;

import com.chenhaonee.wallet.domain.Bill;
import com.chenhaonee.wallet.repository.BillRepository;
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

    public List<Bill> lookup(Date from, Date to) {
        Date fromTime = toZero(from);
        Date toTime = toNight(to);
        return repository.findByCreateTimeBetween(fromTime, toTime);

    }

    public List<Bill> lookup(Date date) {
        Date fromTime = toZero(date);
        Date toTime = toNight(date);
        return repository.findByCreateTimeBetween(fromTime, toTime);
    }


    private Date toZero(Date date) {
        long zero = (date.getTime() - 8 * 3600 * 1000) / (24 * 3600 * 1000) * (24 * 3600 * 1000) + 8 * 3600 * 1000;
        return new Date(zero);
    }

    private Date toNight(Date date) {
        long night = (date.getTime() - 8 * 3600 * 1000) / (24 * 3600 * 1000) * (24 * 3600 * 1000) + 8 * 3600 * 1000 + 24 * 3600 * 1000 - 1;
        return new Date(night);
    }


}
