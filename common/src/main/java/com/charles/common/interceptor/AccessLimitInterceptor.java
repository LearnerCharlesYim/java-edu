package com.charles.common.interceptor;

import cn.hutool.extra.spring.SpringUtil;
import com.charles.common.annotation.AccessLimit;
import com.charles.common.domain.R;
import com.charles.common.utils.IpUtil;
import com.charles.common.utils.WebUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 限流拦截器
 */

@SuppressWarnings("all")
public class AccessLimitInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate redisTemplate = SpringUtil.getBean(StringRedisTemplate.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            AccessLimit accessLimit = method.getMethodAnnotation(AccessLimit.class);
            if (accessLimit != null) {
                int seconds = accessLimit.seconds();
                int maxCount = accessLimit.maxCount();
                String key = String.format("%s:%s", IpUtil.getIpAddr(request), method.getMethod().getName());
                Long count = redisTemplate.opsForValue().increment(key, 1);
                if (count == 1) redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
                if (count > maxCount) {
                    WebUtil.writeJson(response, R.fail("请求过于频繁，请稍后再试"));
                    return false;
                }
            }
        }
        return true;
    }
}
