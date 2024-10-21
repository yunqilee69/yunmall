package icu.yunke.framework.security.config;

import icu.yunke.framework.security.filter.AuthenticationFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 所有依赖common包的微服务，都可以使用PreAuthorize注解进行权限设置
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法权限
public class MethodSecurityConfig {

    @Bean
    @ConditionalOnMissingBean(SecurityFilterChain.class)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationFilter getAuthenticationFilter() {
        return new AuthenticationFilter();
    }

}
