package icu.yunke.framework.security.filter;

import icu.yunke.framework.common.constants.UserConstant;
import icu.yunke.framework.common.context.UserContextHolder;
import icu.yunke.framework.common.model.dto.UserDTO;
import icu.yunke.framework.redis.constant.UserRedisConstant;
import icu.yunke.framework.security.convert.AuthConvert;
import icu.yunke.framework.security.dto.AuthenticationUserDTO;
import org.redisson.api.RedissonClient;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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
public class YunAuthenticationFilter extends OncePerRequestFilter {

    // TODO 封装redisClient
    @Resource
    private RedissonClient redissonClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String userId = request.getHeader(UserConstant.USER_ID);
        if (userId != null && !userId.isEmpty()) {
            UserDTO userDTO = (UserDTO) redissonClient.getBucket(UserRedisConstant.USER_KEY + userId).get();
            // 设置当前线程的用户信息
            UserContextHolder.set(userDTO);

            // 设置Spring Security的认证数据，以便请求通过后续过滤链。
            // 当没有认证数据时，后续的过滤链会将请求拦截，并使用handler包下的处理器进行对应处理
            AuthenticationUserDTO authenticationUserDTO = AuthConvert.INSTANCE.toAuthenticationUserDTO(userDTO);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticationUserDTO, null, authenticationUserDTO.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);

        // 结束前，需要清除当前线程的用户信息，防止出现OOM
        UserContextHolder.clear();
    }

    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        // 返回true表示不对异步请求应用过滤器
        return true;
    }
}
