package icu.yunke.auth.model.convert;

import icu.yunke.auth.model.dto.UserDTO;
import icu.yunke.auth.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConvert {

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);
}
