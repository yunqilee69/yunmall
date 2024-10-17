package icu.yunke.auth.service.impl;

import icu.yunke.auth.controller.response.LoginResponse;
import icu.yunke.auth.mapper.AuthMapper;
import icu.yunke.auth.service.AuthService;
import icu.yunke.framework.common.constants.JwtConstant;
import icu.yunke.framework.common.constants.UserConstant;
import icu.yunke.framework.common.exception.auth.AuthError;
import icu.yunke.framework.common.exception.auth.AuthException;
import icu.yunke.framework.common.exception.base.JwtError;
import icu.yunke.framework.common.util.JwtUtils;
import icu.yunke.framework.redis.constant.UserRedisConstant;
import icu.yunke.framework.security.dto.AuthenticationUserDTO;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private AuthMapper authMapper;

    @Override
    public LoginResponse login(String username, String password) {
        // 组装待验证的用户名和密码
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        // 进行验证，最终逻辑会调用UserDetailServiceImpl的方法
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (authenticate == null) {
            // 登录失败
            throw new AuthException(AuthError.LOGIN_FAILED);
        }
        // 获取登录成功得到的用户数据，并存入redis缓存中
        AuthenticationUserDTO userDTO = (AuthenticationUserDTO) authenticate.getPrincipal();
        redissonClient.getBucket(UserRedisConstant.USER_KEY +userDTO.getUserId()).set(userDTO, 8L, TimeUnit.HOURS);

        return createTokenAfterLoginSuccess(userDTO.getUserId());
    }

    @Override
    public void logout(Long userId) {
        redissonClient.getBucket(UserRedisConstant.USER_KEY + userId).delete();
    }

    @Override
    public LoginResponse refreshToken(String refreshToken) {
        if (JwtUtils.isTokenExpired(refreshToken)) {
            throw new AuthException(JwtError.TOKEN_IS_EXPIRED);
        }
        Long userId = JwtUtils.getUserid(refreshToken);
        // 获取AuthenticationUserDTO
        AuthenticationUserDTO userDTO = getAuthenticationUserDTOByUserId(userId);
        redissonClient.getBucket(UserRedisConstant.USER_KEY +userDTO.getUserId()).set(userDTO, 8L, TimeUnit.HOURS);

        return createTokenAfterLoginSuccess(userId);
    }

    /**
     * 登录成功后，生成token及refreshToken
     * @param userId
     * @return
     */
    LoginResponse createTokenAfterLoginSuccess(Long userId) {
        // 生成token
        Map<String, Object> claims = new HashMap<>(1);
        claims.put(UserConstant.USER_ID, userId);
        Map<String, String> tokenMap = JwtUtils.generateTokenAndRefreshToken(claims);
        return LoginResponse.builder()
                .token(tokenMap.get(JwtConstant.TOKEN))
                .refreshToken(tokenMap.get(JwtConstant.REFRESH_TOKEN))
                .expiresIn(tokenMap.get(JwtConstant.EXPIRES_IN))
                .build();
    }

    @Override
    public AuthenticationUserDTO getAuthenticationUserDTOByUsername(String username) {
        return authMapper.getAuthenticationUserDTOByUsername(username);
    }

    @Override
    public AuthenticationUserDTO getAuthenticationUserDTOByUserId(Long userId) {
        return authMapper.getAuthenticationUserDTOByUserId(userId);
    }

}
