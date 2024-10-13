package com.yunqi.order;

import com.yunqi.common.dubbo.service.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YunOrderApplicationTests {

    @DubboReference
    private HelloService helloService;

    @Test
    void contextLoads() {
        helloService.sayHello("我是order");
    }

}
