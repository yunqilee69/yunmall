package icu.yunke.auth.model.convert;

import icu.yunke.auth.model.entity.User;
import icu.yunke.framework.common.model.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * User转换器
 */
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);
}
