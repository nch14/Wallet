package cn.chenhaonee.wallet.remote.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by chenhaonee at 2019/1/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    public Object t;

    public int code;

    public String msg;

    public static Response success(Object t) {
        Response response = new Response();
        response.t = t;
        response.code = 200;
        response.msg = "Success";
        return response;
    }

    public static Response failure() {
        Response response = new Response();
        response.t = null;
        response.code = 400;
        response.msg = "Failure";
        return response;
    }

    public static Response missingToken() {
        Response response = new Response();
        response.t = null;
        response.code = 600;
        response.msg = "Missing Token";
        return response;
    }

    public static Response invalidToken() {
        Response response = new Response();
        response.t = null;
        response.code = 601;
        response.msg = "连接已断开，请重新登陆";
        return response;
    }
}
