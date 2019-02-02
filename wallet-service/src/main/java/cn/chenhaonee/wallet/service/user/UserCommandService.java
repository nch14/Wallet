package cn.chenhaonee.wallet.service.user;

import cn.chenhaonee.wallet.domain.User;
import cn.chenhaonee.wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenhaonee at 2019/2/2
 */
@Service
public class UserCommandService {

    @Autowired
    private UserRepository repository;

    public User addAccount(String username, String nickname, String password) {
        User user = new User(username, nickname, password);
        user = repository.save(user);
        return user;
    }
}
