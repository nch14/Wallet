package cn.chenhaonee.wallet.domain;

import cn.chenhaonee.wallet.domain.enums.IncomeType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by chenhaonee at 2019/2/1
 */
@Entity
@Data
public class Income {

    @GeneratedValue
    @Id
    private Long id;

    private Long userId;

    /**
     * 收入金额
     */
    private Integer money;

    /**
     * 收入类型
     */
    private IncomeType incomeType;
    /**
     * 收入月份
     */
    private String incomeMonth;
    /**
     * 收入入账时间
     */
    private Date date;

    /**
     * 备注
     */
    private String memo;
}
