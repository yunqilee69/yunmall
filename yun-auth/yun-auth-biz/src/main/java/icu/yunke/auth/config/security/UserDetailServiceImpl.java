package icu.yunke.auth.config.security;

import icu.yunke.auth.service.AuthService;
import icu.yunke.framework.common.exception.auth.AuthError;
import icu.yunke.framework.common.exception.auth.AuthException;
import icu.yunke.framework.security.dto.AuthenticationUserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    @Lazy
    private AuthService authService;

    /**
     * 登录方法
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new AuthException(AuthError.USERNAME_IS_EMPTY);
        }
        return authService.getAuthenticationUserDTOByUsername(username);
    }
}
