package icu.yunke.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.yunke.auth.mapper.AuthMapper;
import icu.yunke.auth.mapper.UserMapper;
import icu.yunke.auth.model.entity.User;
import icu.yunke.auth.service.UserService;
import icu.yunke.framework.security.dto.AuthenticationUserDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户service实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
