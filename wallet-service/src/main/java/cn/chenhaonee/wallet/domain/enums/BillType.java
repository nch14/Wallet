package cn.chenhaonee.wallet.domain.enums;

/**
 * Created by chenhaonee at 2019/2/1
 */
public enum  BillType {
    FOOD("饮食"),
    LIVING("房租"),
    TRANSPORT("交通"),
    SHOPPING("购物"),
    OTHERS("其它");

    public String memo;

    BillType(String memo) {
        this.memo = memo;
    }
}
