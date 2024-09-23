package com.yunqi.yungateway.controller;

import com.yunqi.common.service.TestService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @DubboReference
    private TestService testService;

    @RequestMapping("/test")
    public String test() {
        testService.say();
        return "success";
    }

}
