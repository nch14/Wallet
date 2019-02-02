package cn.chenhaonee.wallet.remote.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by chenhaonee at 2019/2/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    public String token;

    public String nickname;
}
