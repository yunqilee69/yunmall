package com.yunqi.common.web.entity;

import com.yunqi.common.constants.ResponseCode;
import lombok.Data;

/**
 * 控制器统一返回结果
 */
@Data
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

    private static final String SUCCESS_MSG = "success";
    private static final String ERROR_MSG = "error";

    private ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 静态方法，用于创建成功的响应
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(ResponseCode.SUCCESS_CODE, message, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ResponseCode.SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(ResponseCode.SUCCESS_CODE, message, data);
    }

    // 静态方法，用于创建失败的响应
    public static <T> ApiResponse<T> failure() {
        return new ApiResponse<>(ResponseCode.ERROR_CODE, ERROR_MSG, null);
    }

    public static <T> ApiResponse<T> failure(String message) {
        return new ApiResponse<>(ResponseCode.ERROR_CODE, message, null);
    }

    public static <T> ApiResponse<T> failure(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }
}