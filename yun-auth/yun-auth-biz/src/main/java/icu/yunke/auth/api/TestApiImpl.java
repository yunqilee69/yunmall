package icu.yunke.auth.api;

import icu.yunke.framework.common.context.UserContextHolder;
import icu.yunke.framework.common.exception.BaseException;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class TestApiImpl implements TestApi{
    @Override
    public String test() {
        System.out.println("这是提供者输出的信息");
        return "这是提供者提供的信息";
    }
}
