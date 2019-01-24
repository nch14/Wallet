package com.chenhaonee.wallet.remote.request;

import com.chenhaonee.wallet.domain.enums.BillType;

import java.util.Date;

/**
 * Created by chenhaonee at 2019/1/25
 */
public class CreateBillRequest {
    /**
     * 涉及金额
     */
    public double money;

    /**
     * 发生时间
     */
    public Date createTime;

    /**
     * 最迟付款时间
     */
    public Date dueTime;

    /**
     * 账单类型
     */
    public BillType billType;

    /**
     * 备注
     */
    public String mojo;
}
