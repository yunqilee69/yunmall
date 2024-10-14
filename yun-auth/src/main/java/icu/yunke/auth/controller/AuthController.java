package icu.yunke.auth.controller;

import icu.yunke.auth.model.request.LoginRequest;
import icu.yunke.auth.model.response.LoginResponse;
import icu.yunke.framework.common.exception.auth.AuthError;
import icu.yunke.framework.common.exception.auth.AuthException;
import icu.yunke.framework.web.entity.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * 权限控制器，用于登录、登出、用户权限获取
 */
@RestController
@RequestMapping
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    /**
     * 登录
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        // 组装待验证的用户名和密码
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        // 进行验证，最终逻辑会调用UserDetailServiceImpl的方法
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (authenticate == null) {
            // 登录失败
            throw new AuthException(AuthError.LOGIN_FAILED);
        }
        return ApiResponse.success(new LoginResponse());
    }

    /**
     * 登出
     * @param userid
     * @return
     */
    @GetMapping("/logout")
    public ApiResponse<String> logout(Long userid) {
        return ApiResponse.success("登出成功");
    }
}
