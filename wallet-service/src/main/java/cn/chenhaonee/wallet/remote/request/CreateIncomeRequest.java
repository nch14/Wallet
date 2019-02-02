package cn.chenhaonee.wallet.remote.request;

import cn.chenhaonee.wallet.domain.enums.IncomeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by chenhaonee at 2019/2/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateIncomeRequest {

    /**
     * 收入金额
     */
    private double money;
    /**
     * 收入月份
     */
    private Date incomeMonth;
    /**
     * 收入入账时间
     */
    private Date date;
    /**
     * 备注
     */
    private String memo;

    private IncomeType incomeType;
}
