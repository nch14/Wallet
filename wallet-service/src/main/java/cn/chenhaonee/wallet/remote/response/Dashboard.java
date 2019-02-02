package cn.chenhaonee.wallet.remote.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by chenhaonee at 2019/2/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dashboard {

    /**
     * 今日剩余预算
     */
    public double leftForToday;

    /**
     * 本月剩余预算
     */
    public double leftThisMonth;

    /**
     * 本月预算
     */
    public double budgetThisMonth;

    /**
     * 本月支出
     */
    public double usedThisMonth;
    /**
     * 本月收入
     */
    public double incomeThisMonth;

    public double deposit;
}
