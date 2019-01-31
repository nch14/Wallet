package com.chenhaonee.wallet.repository;

import com.chenhaonee.wallet.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by chenhaonee at 2019/1/25
 */
public interface BillRepository extends JpaRepository<Bill,Long> {

    List<Bill> findByCreateTimeBetween(Date from,Date to);
}
