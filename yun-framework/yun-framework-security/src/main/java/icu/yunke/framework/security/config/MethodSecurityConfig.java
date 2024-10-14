package icu.yunke.framework.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 所有依赖common包的微服务，都可以使用PreAuthorize注解进行权限设置
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法权限
public class MethodSecurityConfig {
}
