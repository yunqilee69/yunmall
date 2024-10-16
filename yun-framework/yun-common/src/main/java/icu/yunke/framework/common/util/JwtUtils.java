package icu.yunke.framework.common.util;

import icu.yunke.framework.common.constants.UserConstant;
import icu.yunke.framework.common.exception.BaseException;
import icu.yunke.framework.common.exception.base.JwtError;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    // jwt配置直接写在工具类，防止在各个微服务中重新进行定义

    // subject主题
    private static final String subject = "yunmall";
    // JWT密钥，生产环境应保持安全,需要大于32个字符
    private static final String SECRET_KEY = "5f6g7h8j9k0l1m2n3o4p5q6r7s8t9u0v1w2x3y4z";
    // JWT的过期时间，这里设置为8小时后过期
    private static final long EXPIRATION_TIME = 28800000;
    // 刷新令牌的过期时间，这里设置为7天
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 604800000;
    // 使用一个秘钥来签名令牌，该秘钥也用于验证令牌的签名
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // 生成JWT令牌
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .header().add("typ", "JWT").and() // 设置header
                .subject(subject)
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                //.signWith(KEY, SignatureAlgorithm.HS256)
                .signWith(KEY)
                .compact();
    }

    // 生成JWT刷新令牌
    public static String generateRefreshToken(Map<String, Object> claims) {
        return Jwts.builder()
                .header().add("typ", "JWT").and() // 设置header
                .subject(subject)
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                //.signWith(KEY, SignatureAlgorithm.HS256)
                .signWith(KEY)
                .compact();
    }

    /**
     * 获取token和过期时间以及刷新token
     * @param claims
     * @return
     */
    public static Map<String, Object> generateTokenAndRefreshToken(Map<String, Object> claims) {
        String token = generateToken(claims);
        String refreshToken = generateRefreshToken(claims);
        Map<String, Object> result = new HashMap<>(3);
        result.put("token", token);
        result.put("refreshToken", refreshToken);
        result.put("expire", String.valueOf(System.currentTimeMillis() + EXPIRATION_TIME));
        return result;
    }


    /**
     * 根据刷新token获取新的token
     * @param refreshToken
     * @return
     */
    public static Map<String, Object> generateRefreshToken(String refreshToken) {
        if (isTokenExpired(refreshToken)) {
            // 如果刷新令牌也过期，则需要用户重新登录
            throw new BaseException(JwtError.TOKEN_IS_EXPIRED);
        }
        return generateTokenAndRefreshToken(parseToken(refreshToken));
    }

    // 解析JWT令牌
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * 检查JWT令牌是否过期，过期-true
     * @param token
     * @return
     */
    public static boolean isTokenExpired(String token) {
        Claims claims = parseToken(token);
        Date expiration = claims.getExpiration();
        // 判断是否过期
        return expiration.before(new Date());
    }

    /**
     * 获取过期时间戳
     * @param token
     * @return
     */
    public static Long getExpiration(String token) {
        Claims claims = parseToken(token);
        return claims.getExpiration().getTime();
    }

    /**
     * 获取用户id
     * @param token
     * @return
     */
    public static Long getUserid(String token) {
        if (isTokenExpired(token)) {
            // 如果令牌过期，则需要用户重新登录
            throw new BaseException(JwtError.TOKEN_IS_EXPIRED);
        }
        Claims claims = parseToken(token);
        return claims.get(UserConstant.USER_ID, Long.class);
    }
}