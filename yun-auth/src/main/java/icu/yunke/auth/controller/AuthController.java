package icu.yunke.auth.controller;

import icu.yunke.auth.model.dto.UserDTO;
import icu.yunke.auth.model.request.LoginRequest;
import icu.yunke.auth.model.response.LoginResponse;
import icu.yunke.framework.common.constants.UserConstant;
import icu.yunke.framework.common.exception.auth.AuthError;
import icu.yunke.framework.common.exception.auth.AuthException;
import icu.yunke.framework.common.util.JwtUtils;
import icu.yunke.framework.web.entity.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 权限控制器，用于登录、登出、用户权限获取
 */
@Tag(name = "管理后台 - 认证")
@RestController
@RequestMapping
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    /**
     * 登录
     * @param loginRequest+
     * @return
     */
    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody LoginRequest loginRequest) {
        // 组装待验证的用户名和密码
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        // 进行验证，最终逻辑会调用UserDetailServiceImpl的方法
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (authenticate == null) {
            // 登录失败
            throw new AuthException(AuthError.LOGIN_FAILED);
        }
        UserDTO UserDTO = (UserDTO) authenticate.getPrincipal();
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(UserConstant.USER_ID, UserDTO.getId());
        return ApiResponse.success(JwtUtils.generateTokenAndRefreshToken(claims));
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
