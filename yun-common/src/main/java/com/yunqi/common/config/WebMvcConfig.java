package com.yunqi.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig {

    // TODO 需要一个过滤器，从请求头获取user_id（gateway进行过滤jwt时设置），设置该用户的权限到上下文（需要进行新建）中，
}
