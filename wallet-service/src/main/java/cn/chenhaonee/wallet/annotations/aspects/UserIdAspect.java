package cn.chenhaonee.wallet.annotations.aspects;

import cn.chenhaonee.wallet.annotations.UserId;
import cn.chenhaonee.wallet.infrastructure.MemorySession;
import cn.chenhaonee.wallet.remote.response.Response;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Parameter;
import java.util.Optional;

/**
 * Created by chenhaonee at 2019/2/1
 */
@Aspect
@Component
@Slf4j
public class UserIdAspect {

    @Autowired
    private MemorySession memorySession;

    @Pointcut("@annotation(cn.chenhaonee.wallet.annotations.RequireAuth)")
    public void defineAspect() {

    }

    @Around("defineAspect()")
    public Object advice(ProceedingJoinPoint joinPoint) {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        //获取参数对象
        Object[] args = joinPoint.getArgs();
        //获取方法参数
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Parameter[] parameters = signature.getMethod().getParameters();

        String token = req.getHeader("OAuth");
        int userIdIndex = -1;
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            UserId userId = parameter.getAnnotation(UserId.class);
            if (userId != null) {
                userIdIndex = i;
                break;
            }
        }

        Object result;
        if (userIdIndex == -1) {
            try {
                result = joinPoint.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage(), e.getCause());
            }
            return result;
        } else if (!Strings.isNullOrEmpty(token)) {
            //解析UserId
            Optional<Long> userId = memorySession.auth(token);
            if (userId.isPresent()) {
                args[userIdIndex] = userId.get();

                try {
                    result = joinPoint.proceed(args);
                } catch (Throwable e) {
                    e.printStackTrace();
                    throw new RuntimeException(e.getMessage(), e.getCause());
                }
                return result;
            } else {
                log.warn("Someone try to fetch info but rejected for invalid token {}", token);
                return Response.invalidToken();
            }
        } else {
            //缺少请求头
            log.warn("Someone try to fetch info but rejected for missing token");
            return Response.missingToken();
        }
    }
}
