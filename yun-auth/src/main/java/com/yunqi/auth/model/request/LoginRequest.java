package com.yunqi.auth.model.request;

import lombok.Data;

/**
 * 登录专用
 */
@Data
public class LoginRequest {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

}
