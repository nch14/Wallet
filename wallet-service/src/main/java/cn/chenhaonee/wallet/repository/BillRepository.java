package cn.chenhaonee.wallet.repository;

import cn.chenhaonee.wallet.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by chenhaonee at 2019/1/25
 */
public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findByUserIdAndCreateTimeBetween(Long userId, Date from, Date to);

    List<Bill> findByUserIdAndBillTime(Long userId, String billTime);
}
