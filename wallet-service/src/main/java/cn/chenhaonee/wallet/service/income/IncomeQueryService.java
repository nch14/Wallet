package cn.chenhaonee.wallet.service.income;

import cn.chenhaonee.wallet.domain.Income;
import cn.chenhaonee.wallet.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenhaonee at 2019/2/1
 */
@Service
public class IncomeQueryService {

    @Autowired
    private IncomeRepository repository;

    public List<Income> lookup(Long userId, String incomeMonth) {
        return repository.findByUserIdAndIncomeMonth(userId, incomeMonth);
    }
}
