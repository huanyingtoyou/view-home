package com.lihy.view.file.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author lihy
 * @date 2018/10/24
 */
@SpringBootApplication(scanBasePackages = "com.lihy.view.file")
@EnableEurekaClient
public class ZuulFileUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulFileUploadApplication.class, args);
    }
}
