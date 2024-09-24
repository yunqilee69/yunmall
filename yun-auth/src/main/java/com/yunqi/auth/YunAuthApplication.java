package com.yunqi.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.yunqi") // 扫描Bean路径
public class YunAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunAuthApplication.class, args);
    }

}
