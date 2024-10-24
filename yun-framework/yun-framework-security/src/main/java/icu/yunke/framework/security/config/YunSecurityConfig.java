package icu.yunke.framework.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import icu.yunke.framework.security.filter.YunAuthenticationFilter;
import icu.yunke.framework.security.handler.YunAccessDeniedHandler;
import icu.yunke.framework.security.handler.YunAuthenticationEntryPoint;
import icu.yunke.framework.security.handler.YunLogoutSuccessHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * 所有依赖common包的微服务，都可以使用PreAuthorize注解进行权限设置
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法权限
public class YunSecurityConfig {

    @Resource
    private YunAuthenticationFilter authenticationFilter;

    @Resource
    private YunAuthenticationEntryPoint authenticationEntryPoint;

    @Resource
    private YunAccessDeniedHandler accessDeniedHandler;

    @Resource
    private YunLogoutSuccessHandler logoutSuccessHandler;

    @Bean
    @ConditionalOnMissingBean(SecurityFilterChain.class)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // CSRF禁用，因为不使用session
            .csrf().disable()
            // 禁用HTTP响应标头
            .headers().cacheControl().disable().and()
            // 认证失败处理类
            .exceptionHandling(exception -> exception // 设置异常处理
                    .accessDeniedHandler(accessDeniedHandler) // 访问未授权的资源
                    .authenticationEntryPoint(authenticationEntryPoint)) // 认证失败后处理
            // 基于token，所以不需要session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            // 过滤请求
            .authorizeRequests()
            // 对于登录login 注册register 验证码captchaImage 允许匿名访问
            .antMatchers("/login", "/register", "/refreshToken").permitAll()
            // 静态资源，可匿名访问
            .antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**").permitAll()
            .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**").permitAll()
            // 除上面外的所有请求全部需要鉴权认证
            .anyRequest().authenticated()
            .and()
            .headers().frameOptions().disable();
        // 添加Logout filter，登出进行的处理
        http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
        // 添加统一filter，通过userId设置认证数据
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

}
