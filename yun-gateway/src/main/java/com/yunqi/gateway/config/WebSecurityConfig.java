package com.yunqi.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * spring security配置类
 */
@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {

    /**
     * 将PasswordEncoder和UserDetailService加载到spring的上下文中，spring security会自动使用AuthenticationManager
     *
     * auth服务只需要提供登录方法和刷新token的方法即可，jwt过滤直接在gateway进行统一的安全过滤
     */



    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeExchange()
                .pathMatchers("/login").permitAll()// 登录路由
                .anyExchange().authenticated(); // 其余所有路由都需要认证
        return http.build();
    }

}
