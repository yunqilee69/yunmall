package icu.yunke.framework.security.handler;

import com.alibaba.fastjson2.JSON;
import icu.yunke.framework.common.exception.auth.AuthError;
import icu.yunke.framework.web.entity.ApiResponse;
import icu.yunke.framework.web.util.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证失败或出现异常时，进行的处理
 */
@Component
public class YunAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        // 创建ApiResponse对象
        ApiResponse<String> apiResponse = ApiResponse.failure(AuthError.AUTHENTICATION_IS_FAILED);
        ServletUtils.renderString(response, HttpServletResponse.SC_UNAUTHORIZED, JSON.toJSONString(apiResponse));
    }
}