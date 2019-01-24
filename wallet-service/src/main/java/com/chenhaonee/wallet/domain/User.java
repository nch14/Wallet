package com.chenhaonee.wallet.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by chenhaonee at 2019/1/25
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String password;
}
