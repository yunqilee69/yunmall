package icu.yunke.auth.model.convert;

import icu.yunke.auth.model.entity.User;
import icu.yunke.framework.common.model.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConvert {

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);
}
