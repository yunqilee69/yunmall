package com.yunqi.common.exception;

import com.yunqi.common.constants.ResponseCode;

/**
 * 错误信息接口
 * 使用枚举类继承该接口，命名规则为XXXError，
 */
public interface ErrorMessage {

    // 默认为基础错误编码
    int code = ResponseCode.ERROR_CODE;

    // 默认错误信息
    String message = "服务器出错，请联系管理员！";

    /**
     * 错误编码，不同的业务对应的编码不同，以方便排查错误
     * @return
     */
    default int getCode() {
        return code;
    }

    /**
     * 获取错误信息，是必填的
     * @return
     */
    default String getMessage() {
        return message;
    }

}
