package icu.yunke.framework.security.filter;

import icu.yunke.framework.common.constants.UserConstant;
import org.redisson.api.RedissonClient;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证过滤器
 */
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    // TODO 封装redisClient
    @Resource
    private RedissonClient redissonClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String userId = request.getHeader(UserConstant.USER_ID);
        if (userId == null) {
            // 校验不通过，返回校验失败
        }

        String user = (String) redissonClient.getBucket(UserConstant.USER_ID + ":" + userId).get();
        filterChain.doFilter(request, response);
    }
}
