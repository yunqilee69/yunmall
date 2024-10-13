package com.yunqi.common.exception.auth;

import com.yunqi.common.exception.BaseException;
import com.yunqi.common.exception.ErrorMessage;

public class AuthException extends BaseException {

    public AuthException() {
        super();
    }

    public AuthException(String message) {
        super(message);
    }

    public AuthException(int code , String message) {
        super(code, message);
    }

    public AuthException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

}
