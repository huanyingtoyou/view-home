package com.lihy.view.elk.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author lihy
 * @date 2018/10/18
 */
@SpringBootApplication(scanBasePackages = "com.lihy.view.elk")
@EnableEurekaClient
public class ElkApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ElkApplication.class).web(true).run(args);
    }
}
