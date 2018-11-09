package com.lihy.view.mycat.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * mycat测试
 * 注解SpringBootApplication包含注解ComponentScan
 * @author lihy
 * @date 2018/04/16
 */
@SpringBootApplication(scanBasePackages = {"com.lihy.view.mycat"})
@EnableEurekaClient
@MapperScan("com.lihy.view.mycat.mapper.**")
public class MyCatApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyCatApplication.class, args);
    }
}
