package icu.yunke.auth;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("icu.yunke") // 扫描Bean路径
@EnableDubbo
@EnableDiscoveryClient
public class YunAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunAuthApplication.class, args);
    }

}
