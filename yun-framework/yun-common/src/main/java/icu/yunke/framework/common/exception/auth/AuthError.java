package icu.yunke.framework.common.exception.auth;


import icu.yunke.framework.common.exception.ErrorMessage;

public enum AuthError implements ErrorMessage {

    LOGIN_FAILED(1001, "登录失败"),

    USERNAME_IS_EMPTY(1002, "用户名为空");

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
