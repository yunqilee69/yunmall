package icu.yunke.framework.common.exception.auth;


import icu.yunke.framework.common.exception.BaseException;
import icu.yunke.framework.common.exception.ErrorMessage;

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
