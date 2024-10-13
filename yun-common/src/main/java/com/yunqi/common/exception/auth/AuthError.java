package com.yunqi.common.exception.auth;

import com.yunqi.common.exception.ErrorMessage;

public enum AuthError implements ErrorMessage {

    LOGIN_FAILED(1001, "登录失败");

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
