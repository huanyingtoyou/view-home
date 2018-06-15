package com.lihy.view.view.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 这里可以用SpringCloudApplication注解代替SpringBootApplication，
 * 该注解还包括了EnableDiscoveryClient和EnableCircuitBreaker
 * @author lihy
 * @date 2018/04/16
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lihy.view.view"})
@EnableFeignClients(basePackages = "com.lihy.view.common")
public class ViewApplication {
    public static void main(String[] args) {
        SpringApplication.run(ViewApplication.class, args);
    }
}
