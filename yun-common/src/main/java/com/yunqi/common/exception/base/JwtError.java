package com.yunqi.common.exception.base;

import com.yunqi.common.exception.ErrorMessage;

/**
 * jwt异常
 */
public enum JwtError implements ErrorMessage {

    TOKEN_IS_EXPIRED(1, "token已过期，请重新登录"),
    TOKEN_IS_ILLEGAL(1, "非法token");

    JwtError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;

    private String message;

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
