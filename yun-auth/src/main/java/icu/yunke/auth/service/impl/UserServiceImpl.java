package icu.yunke.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.yunke.auth.mapper.UserMapper;
import icu.yunke.auth.model.entity.User;
import icu.yunke.auth.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * 用户service实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
