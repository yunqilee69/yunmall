package com.yunqi.auth.controller;

import com.yunqi.auth.service.IUserService;
import lombok.AllArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/user")
@AllArgsConstructor
public class UserController {

    private final IUserService userService;

    private final RedissonClient redissonClient;

    @GetMapping("/test")
    public String getAll() {
        return "测试成功";
    }

}
