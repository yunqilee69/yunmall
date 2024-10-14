package icu.yunke.framework.common.exception;

/**
 * 错误信息接口
 * 使用枚举类继承该接口，命名规则为XXXError，
 */
public interface ErrorMessage {


    /*
     * 错误编码，不同的业务对应的编码不同，以方便排查错误
     * @return
     */
    int getCode();

    /**
     * 获取错误信息，是必填的
     * @return
     */
    String getMessage();

}
