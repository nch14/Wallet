package cn.chenhaonee.wallet.annotations;

import java.lang.annotation.*;

/**
 * Created by chenhaonee at 2019/2/1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireAuth {
}
