package com.yunqi.auth.controller;

import com.yunqi.auth.mapper.UserMapper;
import com.yunqi.auth.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/user")
@AllArgsConstructor
public class UserController {

    private final UserMapper userMapper;

    @GetMapping
    public User getOne(Long id) {
        return userMapper.getOne(id);
    }

}
