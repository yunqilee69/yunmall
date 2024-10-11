package com.yunqi.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunqi.auth.mapper.RoleMapper;
import com.yunqi.auth.model.entity.Role;
import com.yunqi.auth.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色service实现类
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
}
