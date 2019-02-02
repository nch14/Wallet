package cn.chenhaonee.wallet.service.income;

import cn.chenhaonee.wallet.domain.Income;
import cn.chenhaonee.wallet.mapper.IncomeMapper;
import cn.chenhaonee.wallet.remote.request.CreateIncomeRequest;
import cn.chenhaonee.wallet.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenhaonee at 2019/2/1
 */
@Service
public class IncomeCommandService {

    @Autowired
    private IncomeRepository repository;

    public Long addIncome(long userId, CreateIncomeRequest request) {
        Income income = IncomeMapper.toIncome(userId, request);
        income = repository.save(income);
        return income.getId();
    }
}
