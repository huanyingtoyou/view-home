package com.lihy.view.sharding.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * java获取数据源的方法
 * 注解SpringBootApplication包含注解ComponentScan
 * 注解SpringBootApplication包含注解EnableAutoConfiguration
 * @author lihy
 * @date 2018/04/16
 */
//@SpringBootApplication(scanBasePackages = {"com.lihy.view.sharding"}, exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = {"com.lihy.view.sharding"})
@EnableEurekaClient
//@MapperScan("com.lihy.view.sharding.mapper.**")
//为了防止代码的自动配置
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@EnableTransactionManagement(proxyTargetClass = true)
public class ShardingServiceJavaYmlApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingServiceJavaYmlApplication.class, args);
    }
}
