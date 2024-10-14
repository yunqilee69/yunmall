package icu.yunke.framework.web.exception;

import icu.yunke.framework.common.exception.BaseException;
import icu.yunke.framework.web.entity.ApiResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * 全局异常处理器，返回错误到前端
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // TODO 补充异常日志输出，还未设计分布式日志系统

    /**
     * 统一捕获sql异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(SQLException.class)
    public ApiResponse<String> handleSQLException(SQLException e) {
        e.printStackTrace();
        return ApiResponse.failure("数据库执行出错");
    }

    /**
     * 捕获业务异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public ApiResponse<String> handleBaseException(BaseException e) {
        e.printStackTrace();
        return ApiResponse.failure(e.getCode(), e.getMessage());
    }

}
