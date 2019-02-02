package cn.chenhaonee.wallet.repository;

import cn.chenhaonee.wallet.domain.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by chenhaonee at 2019/2/1
 */
public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByUserIdAndIncomeMonth(Long userId, String incomeMonth);

}
