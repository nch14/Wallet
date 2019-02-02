package cn.chenhaonee.wallet.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by chenhaonee at 2019/1/25
 */
@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String nickname;

    private String password;

    public User(String username, String nickname, String password) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
    }
}


