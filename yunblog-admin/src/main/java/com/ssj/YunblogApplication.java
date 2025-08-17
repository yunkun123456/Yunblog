package com.ssj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ssj.yunblog.*")
@MapperScan("com.ssj.yunblog.*.dao")
public class YunblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunblogApplication.class, args);
    }

}
