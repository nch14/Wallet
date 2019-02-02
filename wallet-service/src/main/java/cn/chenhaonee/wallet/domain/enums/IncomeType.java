package cn.chenhaonee.wallet.domain.enums;

/**
 * Created by chenhaonee at 2019/2/1
 */
public enum  IncomeType {
    Salary("工资"),Bonus("奖金"),Interest("利息"),Others("其它");

    public String name;

    IncomeType(String name) {
        this.name = name;
    }}
