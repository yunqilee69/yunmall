package com.yunqi.auth.service.impl;
import com.yunqi.common.service.HelloService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@DubboService(group = "dubbo_group")
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
}
