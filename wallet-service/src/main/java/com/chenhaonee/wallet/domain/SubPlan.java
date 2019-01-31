package com.chenhaonee.wallet.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by chenhaonee at 2019/2/1
 */
@Entity
@Data
public class SubPlan {

    /**
     * 自增id
     */
    @GeneratedValue
    @Id
    private Long id;

    private Long planId;

    private String name;

    private Integer budget;

}
