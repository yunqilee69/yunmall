package com.yunqi.auth.service.impl;
import com.yunqi.auth.model.entity.User;
import com.yunqi.common.service.TestService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@DubboService
@Component
public class TestServiceImpl implements TestService {

    @Override
    public void say() {
        System.out.println("Hello dubbo");
    }
}
