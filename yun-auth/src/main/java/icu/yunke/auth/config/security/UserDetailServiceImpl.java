package icu.yunke.auth.config.security;

import icu.yunke.auth.model.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    /**
     * 登录方法
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO 实现登录逻辑
        UserDTO userDTO = new UserDTO();
        if (!"admin".equals(username)) {
            throw new UsernameNotFoundException(username);
        }
        userDTO.setUsername(username);
        userDTO.setPassword("$2a$06$HD.bS3j6GZqV1ru/pK37qOAOFdz6NV2mHYALe3/X1Y9i27Lem6zUW");
        userDTO.setId(123456L);
        return userDTO;
    }
}
