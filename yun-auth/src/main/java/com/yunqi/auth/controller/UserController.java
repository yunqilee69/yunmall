package com.yunqi.auth.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunqi.auth.model.convert.UserConvert;
import com.yunqi.auth.model.dto.UserDTO;
import com.yunqi.auth.model.entity.User;
import com.yunqi.auth.service.IUserService;
import com.yunqi.common.util.PageUtil;
import com.yunqi.common.web.entity.RequestPage;
import com.yunqi.common.web.entity.ResponsePage;
import com.yunqi.common.web.interfaces.BaseController;
import lombok.AllArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth/user")
@AllArgsConstructor
public class UserController implements BaseController<UserDTO> {

    private final IUserService userService;

    private final RedissonClient redissonClient;

    private final UserConvert userConvert;

    @Override
    public ResponsePage<UserDTO> findPage(UserDTO userDTO) {
        Page<User> page = PageUtil.getPage(User.class);
        page = userService.page(page);
        List<UserDTO> records = page.getRecords().stream().map(userConvert::userToUserDTO).collect(Collectors.toList());
        return PageUtil.convert2ResponsePage(page, records);
    }

    @Override
    public void create(@RequestBody UserDTO userDTO) {
        userService.save(userConvert.userDTOToUser(userDTO));
    }

    @Override
    public void update(@RequestBody UserDTO userDTO) {
        userService.updateById(userConvert.userDTOToUser(userDTO));
    }

    @Override
    public void delete(@RequestBody List<Long> ids) {
        userService.removeBatchByIds(ids);
    }

    @Override
    public UserDTO findById(@PathVariable Long id) {
        return userConvert.userToUserDTO(userService.getById(id));
    }
}
