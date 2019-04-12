package com.honour.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.honour.manager.dao")
public class HonourManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HonourManagerApplication.class, args);
    }

}
