package icu.yunke.framework.common.exception.auth;


import icu.yunke.framework.common.exception.ErrorMessage;

public enum AuthError implements ErrorMessage {

    LOGIN_FAILED(1001, "登录失败"),

    USERNAME_IS_EMPTY(1002, "用户名为空"),
    AUTHENTICATION_IS_FAILED(1003, "认证失败，请重新登录"),
    NOT_PERMISSION(1004, "权限不足，请联系管理员"),
    USERNAME_OR_PASSWORD_IS_ERROR(1005, "账号或密码错误，请重试");

    AuthError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // 错误编码
    private final int code;

    // 错误信息
    private final String message;


    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
