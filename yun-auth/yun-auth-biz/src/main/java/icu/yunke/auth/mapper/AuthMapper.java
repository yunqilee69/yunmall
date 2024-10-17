package icu.yunke.auth.mapper;

import icu.yunke.framework.security.dto.AuthenticationUserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {


    AuthenticationUserDTO getAuthenticationUserDTOByUserId(Long userId);

    AuthenticationUserDTO  getAuthenticationUserDTOByUsername(String username);

}
