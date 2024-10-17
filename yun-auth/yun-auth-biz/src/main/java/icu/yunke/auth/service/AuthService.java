package icu.yunke.auth.service;

import icu.yunke.auth.controller.response.LoginResponse;
import icu.yunke.framework.security.dto.AuthenticationUserDTO;

/**
 * 权限服务
 * 登录、登出、刷新token所需的方法
 */
public interface AuthService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    LoginResponse login(String username, String password);

    /**
     * 登出
     * @param userId
     */
    void logout(Long userId);

    /**
     * 刷新token
     * @param refreshToken
     * @return
     */
    LoginResponse refreshToken(String refreshToken);


    /**
     * 根据用户名获取AuthenticationUserDTO
     * @param userId
     * @return
     */
    AuthenticationUserDTO getAuthenticationUserDTOByUserId(Long userId);

    /**
     * 根据用户名获取AuthenticationUserDTO
     * @param username
     * @return
     */
    AuthenticationUserDTO getAuthenticationUserDTOByUsername(String username);
}
