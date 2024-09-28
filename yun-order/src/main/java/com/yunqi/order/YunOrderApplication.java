package com.yunqi.order;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.yunqi") // 扫描Bean路径
@EnableDubbo
public class YunOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunOrderApplication.class, args);
    }

}
