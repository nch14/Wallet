package cn.chenhaonee.wallet.service.account;

import cn.chenhaonee.wallet.domain.User;
import cn.chenhaonee.wallet.infrastructure.MemorySession;
import cn.chenhaonee.wallet.remote.response.LoginResponse;
import cn.chenhaonee.wallet.service.user.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by chenhaonee at 2019/2/2
 */
@Service
public class AccountQueryService {

    @Autowired
    private UserQueryService userQueryService;

    @Autowired
    private MemorySession memorySession;

    public Optional<LoginResponse> processLogin(String username, String password) {
        User user = userQueryService.loadUser(username);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                String token = memorySession.makeToken(user.getId());
                return Optional.of(new LoginResponse(token, user.getNickname()));
            }
        }
        return Optional.empty();
    }
}
