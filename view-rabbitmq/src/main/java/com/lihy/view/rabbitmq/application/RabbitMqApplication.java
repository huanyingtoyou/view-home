package com.lihy.view.rabbitmq.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "com.lihy.view.rabbitmq")
@EnableEurekaClient
public class RabbitMqApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApplication.class, args);
    }
}
