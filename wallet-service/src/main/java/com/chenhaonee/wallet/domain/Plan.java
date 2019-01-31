package com.chenhaonee.wallet.domain;

import com.chenhaonee.wallet.domain.enums.PlanStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by chenhaonee at 2019/2/1
 */
@Entity
@Data
public class Plan {

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

    private String name;

    private Integer budget;

    private PlanStatus status;
}
