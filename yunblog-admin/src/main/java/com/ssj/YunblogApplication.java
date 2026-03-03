package com.ssj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.ssj.yunblog.*")
@MapperScan("com.ssj.yunblog.*.dao")
@EnableAspectJAutoProxy
@EnableScheduling
public class YunblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunblogApplication.class, args);
    }

}
