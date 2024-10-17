package icu.yunke.auth;

import icu.yunke.auth.service.AuthService;
import icu.yunke.framework.security.dto.AuthenticationUserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class YunAuthBizApplicationTests {

    @Resource
    AuthService authService;

    @Test
    void contextLoads() {
        AuthenticationUserDTO authenticationUserDTOByUserId = authService.getAuthenticationUserDTOByUserId(1L);
        System.out.println(authenticationUserDTOByUserId);
    }

}
