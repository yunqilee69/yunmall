package com.yunqi.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunqi.auth.mapper.MenuMapper;
import com.yunqi.auth.model.entity.Menu;
import com.yunqi.auth.service.IMenuService;
import org.springframework.stereotype.Service;

/**
 * 菜单service实现类
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
}
