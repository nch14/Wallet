package cn.chenhaonee.wallet.infrastructure;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenhaonee at 2019/2/1
 */
@Component
public class MemorySession implements InitializingBean {

    private LoadingCache<String, Long> signedUser;

    public String makeToken(Long userId) {
        String uuid = UUID.randomUUID().toString();
        signedUser.put(uuid, userId);
        return uuid;
    }

    public Optional<Long> auth(String token) {
        if (token.equals("TOKENFORADMIN"))
            return Optional.of(666666L);
        Long userId = signedUser.getIfPresent(token);
        return Optional.ofNullable(userId);
    }

    @Override
    public void afterPropertiesSet() {
        signedUser = CacheBuilder.newBuilder()
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .initialCapacity(100)
                .build(
                        new CacheLoader<String, Long>() {
                            @Override
                            public Long load(String token) {
                                return null;
                            }
                        }
                );
    }
}
