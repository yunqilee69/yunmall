package com.yunqi.auth.model.convert;

import com.yunqi.auth.model.dto.UserDTO;
import com.yunqi.auth.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConvert {

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);
}
