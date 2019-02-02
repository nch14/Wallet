package cn.chenhaonee.wallet.remote.request;

import lombok.Data;

/**
 * Created by chenhaonee at 2019/2/2
 */
@Data
public class CreateUserRequest {

    public String username;

    public String password;

    public String nickname;
}
