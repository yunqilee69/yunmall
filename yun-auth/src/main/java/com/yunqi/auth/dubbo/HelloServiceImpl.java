package com.yunqi.auth.dubbo;
import com.yunqi.common.service.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(group = "dubbo_group")
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
}
