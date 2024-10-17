package icu.yunke.auth.controller;

import icu.yunke.auth.controller.request.LoginRequest;
import icu.yunke.auth.controller.response.LoginResponse;
import icu.yunke.auth.service.AuthService;
import icu.yunke.framework.common.util.JwtUtils;
import icu.yunke.framework.redis.constant.UserRedisConstant;
import icu.yunke.framework.web.entity.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 权限控制器，用于登录、登出、用户权限获取
 */
@Tag(name = "管理后台 - 认证")
@RestController
@RequestMapping
public class AuthController {

    @Resource
    private AuthService authService;

    /**
     * 登录
     * @param loginRequest+
     * @return
     */
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ApiResponse.success(authService.login(loginRequest.getUsername(), loginRequest.getPassword()));
    }

    /**
     * 登出
     * @param userid
     * @return
     */
    @GetMapping("/logout")
    public ApiResponse<String> logout(Long userid) {
        authService.logout(userid);
        return ApiResponse.success("登出成功");
    }

    /**
     * 根据refreshToken重新生成token和refreshToken
     * @param refreshToken
     * @return
     */
    @PostMapping("/refreshToken")
    public ApiResponse<LoginResponse> refreshToken(@RequestBody String refreshToken) {
        return ApiResponse.success(authService.refreshToken(refreshToken));
    }
}
