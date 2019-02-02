package cn.chenhaonee.wallet.service.account;

import cn.chenhaonee.wallet.domain.User;
import cn.chenhaonee.wallet.infrastructure.MemorySession;
import cn.chenhaonee.wallet.service.user.UserCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenhaonee at 2019/2/2
 */
@Service
public class AccountCommandService {

    @Autowired
    private UserCommandService userCommandService;

    @Autowired
    private MemorySession memorySession;

    public User addAccount(String username, String nickname, String password) {
        User user = userCommandService.addAccount(username, nickname, password);
        return user;
    }
}
