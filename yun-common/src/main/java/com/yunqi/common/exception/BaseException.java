package com.yunqi.common.exception;

import lombok.Getter;

public class BaseException extends RuntimeException {

    @Getter
    int code;

    String message;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    public BaseException(int code , String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.code = errorMessage.getCode();
        this.message = errorMessage.getMessage();
    }

}
