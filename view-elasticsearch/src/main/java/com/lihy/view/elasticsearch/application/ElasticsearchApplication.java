package com.lihy.view.elasticsearch.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author lihy
 * @date 2018/10/31
 */
@SpringBootApplication(scanBasePackages = "com.lihy.view")
@EnableEurekaClient
@MapperScan("com.lihy.view.elasticsearch.mapper.**")
public class ElasticsearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }
}
