package icu.yunke.auth.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.yunke.auth.model.convert.UserConvert;
import icu.yunke.auth.model.entity.User;
import icu.yunke.auth.service.UserService;
import icu.yunke.framework.common.model.dto.UserDTO;
import icu.yunke.framework.web.entity.ResponsePage;
import icu.yunke.framework.web.interfaces.BaseController;
import lombok.AllArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth/user")
@AllArgsConstructor
public class UserController implements BaseController<UserDTO> {

    // TODO 返回类都需要重新新建并设置MapStruct

    private final UserService userService;

    private final RedissonClient redissonClient;

    @Override
    public ResponsePage<UserDTO> findPage(UserDTO userDTO) {
        Page<User> page = getPage(User.class);
        page = userService.page(page);
        List<UserDTO> records = page.getRecords().stream().map(UserConvert.INSTANCE::toUserDTO).collect(Collectors.toList());
        return convert2ResponsePage(page, records);
    }

    @Override
    public void create(@RequestBody UserDTO userDTO) {
        userService.save(UserConvert.INSTANCE.toUser(userDTO));
    }

    @Override
    public void update(@RequestBody UserDTO userDTO) {
        userService.updateById(UserConvert.INSTANCE.toUser(userDTO));
    }

    @Override
    public void delete(@RequestBody List<Long> ids) {
        userService.removeBatchByIds(ids);
    }

    @Override
    public UserDTO findById(@PathVariable Long id) {
        return UserConvert.INSTANCE.toUserDTO(userService.getById(id));
    }
}
