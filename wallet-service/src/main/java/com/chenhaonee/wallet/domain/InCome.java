package com.chenhaonee.wallet.domain;

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
public class InCome {

    @GeneratedValue
    @Id
    private Long id;

    private Long userId;

    /**
     * 收入金额
     */
    private Integer money;
    /**
     * 收入月份
     */
    private Date month;
    /**
     * 收入入账时间
     */
    private Date date;
}
