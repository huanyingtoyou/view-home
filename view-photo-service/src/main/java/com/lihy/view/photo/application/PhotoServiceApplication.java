package com.lihy.view.photo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lihy
 * @date 2018/10/11
 */
@SpringBootApplication(scanBasePackages = {"com.lihy.view"})
@EnableEurekaClient
//@ComponentScan(basePackages = {"com.lihy.view.photo"})
@EnableFeignClients(basePackages = "com.lihy.view.api")
@EnableCircuitBreaker
public class PhotoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PhotoServiceApplication.class, args);
    }
}
