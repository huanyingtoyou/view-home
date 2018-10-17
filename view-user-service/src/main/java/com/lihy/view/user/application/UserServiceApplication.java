package com.lihy.view.user.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

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
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
