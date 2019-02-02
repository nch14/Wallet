package cn.chenhaonee.wallet.service.user;

import cn.chenhaonee.wallet.domain.User;
import cn.chenhaonee.wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by chenhaonee at 2019/2/2
 */
@Service
public class UserQueryService {

    @Autowired
    private UserRepository repository;

    public User loadUser(String username) {
        return repository.findByUsername(username);
    }
}
