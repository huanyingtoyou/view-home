package com.lihy.view.user.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 用户微服务
 *
 * 注解SpringBootApplication包含注解ComponentScan
 * @author lihy
 * @date 2018/04/16
 */
@SpringBootApplication(scanBasePackages = {"com.lihy.view.user"})
@EnableEurekaClient
//@ComponentScan(basePackages = {"com.lihy.view.user"})
@MapperScan("com.lihy.view.user.mapper.**")
public class UserServiceSecondApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceSecondApplication.class, args);
    }
}
