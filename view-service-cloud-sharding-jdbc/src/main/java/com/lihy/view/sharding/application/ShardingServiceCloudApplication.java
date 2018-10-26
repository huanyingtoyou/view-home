package com.lihy.view.sharding.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * SpringCloud整合sharding-jdbc
 * 注解SpringBootApplication包含注解ComponentScan
 * @author lihy
 * @date 2018/04/16
 */
@SpringBootApplication(scanBasePackages = {"com.lihy.view.sharding"})
@EnableEurekaClient
@MapperScan("com.lihy.view.sharding.mapper.**")
public class ShardingServiceCloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingServiceCloudApplication.class, args);
    }
}
