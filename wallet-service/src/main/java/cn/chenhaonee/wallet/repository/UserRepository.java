package cn.chenhaonee.wallet.repository;

import cn.chenhaonee.wallet.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by chenhaonee at 2019/2/2
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
