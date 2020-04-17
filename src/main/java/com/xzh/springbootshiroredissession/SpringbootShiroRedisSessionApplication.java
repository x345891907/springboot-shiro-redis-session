package com.xzh.springbootshiroredissession;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xzh.springbootshiroredissession.dao")
public class SpringbootShiroRedisSessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShiroRedisSessionApplication.class, args);
    }

}