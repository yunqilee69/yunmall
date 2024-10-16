package icu.yunke.auth;

import icu.yunke.framework.common.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class YunAuthApplicationTest {

    @Test
    void testYunAuth() {
        System.out.println("Test");
        Map<String, Object> map = new HashMap<>();
        map.put("userId", "123456");
        map.put("password", "123456");
        String token = JwtUtils.generateToken(map);
        System.out.println(token);
        System.out.println("userId:" + JwtUtils.getUserid(token));
        Claims claims = JwtUtils.parseToken(token);
        System.out.println(claims.getSubject());
        System.out.println(claims.getExpiration());
    }

}
