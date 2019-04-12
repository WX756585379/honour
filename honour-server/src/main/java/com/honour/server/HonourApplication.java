package com.honour.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync //开启异步注解功能
@EnableScheduling
@SpringBootApplication
// @EnableTransactionManagement
@MapperScan("com.honour.server.dao")
public class HonourApplication {

    // https://www.jianshu.com/p/a7b6aeb30685
    // 在线文档 http://localhost:8087/swagger-ui.html
    public static void main(String[] args) {
        SpringApplication.run(HonourApplication.class, args);
    }

}
