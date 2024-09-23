package com.yunqi.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunqi.auth.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User getOne(Long id);

}
