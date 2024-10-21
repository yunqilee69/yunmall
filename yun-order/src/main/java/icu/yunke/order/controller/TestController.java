package icu.yunke.order.controller;

import icu.yunke.auth.api.TestApi;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "测试")
@RestController
@RequestMapping("/order/test")
@AllArgsConstructor
public class TestController {

    @DubboReference
    TestApi testApi;

    @GetMapping
    public String hello(String name) {
        return testApi.test();
    }

}
