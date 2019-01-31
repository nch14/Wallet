package com.chenhaonee.wallet.remote.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by chenhaonee at 2019/1/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    public T t;

    public boolean success;

}
