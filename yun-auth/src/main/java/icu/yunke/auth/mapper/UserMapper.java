package icu.yunke.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.yunke.auth.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户mapper
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
