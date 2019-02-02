package cn.chenhaonee.wallet.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by chenhaonee at 2019/2/1
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Binary<A,B> {

    public A a;

    public B b;
}
