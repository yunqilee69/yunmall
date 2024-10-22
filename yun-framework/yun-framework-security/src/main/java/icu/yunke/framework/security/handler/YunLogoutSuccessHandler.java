package icu.yunke.framework.security.handler;

import com.alibaba.fastjson2.JSON;
import icu.yunke.framework.common.exception.auth.AuthError;
import icu.yunke.framework.web.entity.ApiResponse;
import icu.yunke.framework.web.util.ServletUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class YunLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 创建ApiResponse对象
        ApiResponse<String> apiResponse = ApiResponse.success("登出成功");
        ServletUtils.renderString(response, JSON.toJSONString(apiResponse));
    }
}
