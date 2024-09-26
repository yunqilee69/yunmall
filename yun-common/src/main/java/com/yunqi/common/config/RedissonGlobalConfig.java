package com.yunqi.common.config;

import lombok.AllArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Redisson配置
 * 目前使用单机部署模式，后续要更改部署方式
 * 现阶段以打通项目为主
 */
@Configuration
@AllArgsConstructor
public class RedissonGlobalConfig {

    /**
     * 直接使用spring-data-redis提供的redis属性类即可
     */
    private final RedisProperties redisProperties;

    private final String redisPrefix = "redis://";

    /**
     * 单机配置
     * @return 返回RedissonClient
     */
    @Bean(destroyMethod = "shutdown")
    RedissonClient singleRedisson() {
        // 创建配置
        Config config = new Config();
        // 使用redisson提供的JsonJacksonCodec序列化器
        config.setCodec(new JsonJacksonCodec());
        // 使用单节点模式
        SingleServerConfig singleServerConfig = config.useSingleServer()
                // 设置连接地址：redis://ip:port
                .setAddress(redisPrefix + redisProperties.getHost() + ":" + redisProperties.getPort())
                .setDatabase(redisProperties.getDatabase());
        // 设置username和password
        if (redisProperties.getUsername() != null) {
            singleServerConfig.setUsername(redisProperties.getUsername());
        }
        if (redisProperties.getPassword() != null) {
            singleServerConfig.setPassword(redisProperties.getPassword());
        }

        return Redisson.create(config);
    }

}
