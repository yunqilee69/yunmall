package com.yunqi.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunqi.auth.mapper.UserMapper;
import com.yunqi.auth.model.entity.User;
import com.yunqi.auth.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * 用户service实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
