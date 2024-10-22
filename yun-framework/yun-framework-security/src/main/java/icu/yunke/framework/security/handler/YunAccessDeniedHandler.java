package icu.yunke.framework.security.handler;

import com.alibaba.fastjson2.JSON;
import icu.yunke.framework.common.exception.auth.AuthError;
import icu.yunke.framework.web.entity.ApiResponse;
import icu.yunke.framework.web.util.ServletUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 访问没有权限的资源时，进行返回处理
 */
@Component
public class YunAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        // 创建ApiResponse对象
        ApiResponse<String> apiResponse = ApiResponse.failure(AuthError.NOT_PERMISSION);
        ServletUtils.renderString(response, HttpServletResponse.SC_FORBIDDEN, JSON.toJSONString(apiResponse));
    }
}
