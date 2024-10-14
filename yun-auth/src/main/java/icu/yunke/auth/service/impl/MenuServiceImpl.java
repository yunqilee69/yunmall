package icu.yunke.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.yunke.auth.mapper.MenuMapper;
import icu.yunke.auth.model.entity.Menu;
import icu.yunke.auth.service.IMenuService;
import org.springframework.stereotype.Service;

/**
 * 菜单service实现类
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
}
