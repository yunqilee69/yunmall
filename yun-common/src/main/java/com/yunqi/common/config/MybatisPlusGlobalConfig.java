package com.yunqi.common.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.yunqi.common.util.SnowflakeIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusGlobalConfig {

    /**
     * 自定义id生成策略
     */
    @Bean
    public IdentifierGenerator identifierGenerator() {
        return new SnowflakeIdGenerator(1L, 1L);
    }

}
