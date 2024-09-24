package com.yunqi.auth.controller;

import com.yunqi.auth.model.entity.User;
import com.yunqi.auth.service.IUserService;
import com.yunqi.common.util.SnowflakeIdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth/user")
@AllArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/testid")
    public void getAll() {
        User user = new User();
        user.setNickname("test1");
        user.setPassword("123456");
        user.setCreateAt(LocalDateTime.now());
        user.setCreateBy(1L);
        user.setUpdateAt(LocalDateTime.now());
        user.setUpdateBy(1L);
        userService.save(user);
    }

}
