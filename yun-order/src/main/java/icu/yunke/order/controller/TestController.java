package icu.yunke.order.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "测试")
@RestController
@RequestMapping("/order/test")
@AllArgsConstructor
public class TestController {


    @GetMapping
    public String hello(String name) {
        return name;
    }

}
