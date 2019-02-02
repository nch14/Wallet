package cn.chenhaonee.wallet.mapper;

import cn.chenhaonee.wallet.domain.Income;
import cn.chenhaonee.wallet.domain.enums.IncomeType;
import cn.chenhaonee.wallet.remote.request.CreateIncomeRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * Created by chenhaonee at 2019/2/1
 */
public class IncomeMapper {

    public static Income toIncome(long userId, CreateIncomeRequest request) {
        Income income = new Income();
        income.setDate(Optional.ofNullable(request.getDate()).orElse(new Date()));
        income.setMoney((int) (request.getMoney() * 100));
        income.setUserId(userId);
        String incomeMonth = new SimpleDateFormat("yyyy-MM").format(Optional.ofNullable(request.getIncomeMonth()).orElse(income.getDate()));
        income.setIncomeMonth(incomeMonth);
        income.setMemo(request.getMemo());
        income.setIncomeType(Optional.ofNullable(request.getIncomeType()).orElse(IncomeType.Others));
        return income;
    }
}
