package icu.yunke.framework.security.convert;

import icu.yunke.framework.common.model.dto.UserDTO;
import icu.yunke.framework.security.dto.AuthenticationUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Auth转换类
 */
@Mapper
public interface AuthConvert {

    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);

    @Mapping(target = "id", source = "userId")
    UserDTO toUserDTO(AuthenticationUserDTO authenticationUserDTO);

}
