package com.honour.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
// @EnableTransactionManagement
@MapperScan("com.honour.server.dao")
public class HonourApplication {

    // 在线文档 http://localhost:8087/swagger-ui.html
    public static void main(String[] args) {
        SpringApplication.run(HonourApplication.class, args);
    }

}
