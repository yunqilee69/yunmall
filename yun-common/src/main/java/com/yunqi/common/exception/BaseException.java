package com.yunqi.common.exception;

import lombok.Getter;

import java.io.Serializable;

public class BaseException extends Exception {

    @Getter
    int code = ErrorMessage.code;

    String message = ErrorMessage.message;

    BaseException() {
        super(ErrorMessage.message);
    }

    BaseException(String message) {
        super(message);
        this.message = message;
    }

    BaseException(int code , String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    BaseException(ErrorMessage errorMessage) {
        super(errorMessage.message);
        this.code = errorMessage.code;
        this.message = errorMessage.message;
    }

}
