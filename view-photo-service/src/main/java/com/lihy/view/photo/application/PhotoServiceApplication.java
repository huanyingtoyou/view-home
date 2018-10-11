package com.lihy.view.photo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lihy
 * @date 2018/10/11
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.lihy.view.photo"})
public class PhotoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PhotoServiceApplication.class, args);
    }
}
