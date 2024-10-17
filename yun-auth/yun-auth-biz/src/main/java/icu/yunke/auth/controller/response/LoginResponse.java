package icu.yunke.auth.controller.response;

import lombok.Builder;
import lombok.Data;

/**
 * 登录返回
 */
@Data
@Builder
public class LoginResponse {

    private String token;

    private String refreshToken;

    private String expiresIn;

}
