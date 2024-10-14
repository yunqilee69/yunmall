package icu.yunke.order.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/test")
@AllArgsConstructor
public class TestController {


    @GetMapping
    public String hello(String name) {
        return name;
    }

}
