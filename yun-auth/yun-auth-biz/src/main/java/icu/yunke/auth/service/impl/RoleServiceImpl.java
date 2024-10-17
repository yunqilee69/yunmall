package icu.yunke.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.yunke.auth.mapper.RoleMapper;
import icu.yunke.auth.model.entity.Role;
import icu.yunke.auth.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * 角色service实现类
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
