package com.lihy.view.turbine.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * turbine聚合监控
 * 也可以通过消息中间件如rabbitmq来收集数据
 * @author lihy
 * @date 2018/10/18
 */
@SpringBootApplication
@EnableEurekaClient
@EnableTurbine
public class HystrixTurbineApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixTurbineApplication.class, args);
    }
}
