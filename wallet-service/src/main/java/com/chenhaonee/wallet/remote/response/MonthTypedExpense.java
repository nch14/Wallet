package com.chenhaonee.wallet.remote.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by chenhaonee at 2019/2/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthTypedExpense {

    private List<TypedExpense> expenses;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TypedExpense{

        private String type;

        private double money;
    }
}
