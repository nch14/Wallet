package cn.chenhaonee.wallet.remote.response;

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
public class HalfYearExpense {

    public List<MonthExpense> expenses;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MonthExpense {

        private String month;

        private double money;
    }
}
