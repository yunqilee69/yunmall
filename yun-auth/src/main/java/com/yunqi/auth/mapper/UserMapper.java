package com.yunqi.auth.mapper;

import com.yunqi.auth.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User getOne(Long id);

}
