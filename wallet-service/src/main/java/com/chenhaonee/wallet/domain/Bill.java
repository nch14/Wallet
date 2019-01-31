package com.chenhaonee.wallet.domain;

import com.chenhaonee.wallet.domain.enums.BillType;
import com.chenhaonee.wallet.domain.enums.PalType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chenhaonee at 2019/1/25
 */
@Entity
@Data
public class Bill {
    /**
     * 自增id
     */
    @GeneratedValue
    @Id
    private Long id;

    /**
     * 关联的用户id
     */
    private long userId;

    /**
     * 涉及金额
     */
    private Integer money;

    /**
     * 发生时间
     */
    private Date createTime;

    /**
     * 最迟付款时间
     */
    private Date dueTime;

    /**
     * 支付类型
     */
    @Enumerated(EnumType.STRING)
    private PalType palType;

    /**
     * 账单类型
     */
    @Enumerated(EnumType.STRING)
    private BillType billType;

    /**
     * 备注
     */
    private String memo;
}
