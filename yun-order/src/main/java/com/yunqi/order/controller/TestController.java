package com.yunqi.order.controller;

import com.yunqi.common.service.HelloService;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/test")
@AllArgsConstructor
public class TestController {

    @DubboReference(group = "dubbo_group")
    private final HelloService helloService;

    @GetMapping
    public void hello(String name) {
        helloService.sayHello(name);
    }

}
