package com.lihy.view.zuul.application;

import com.lihy.view.zuul.filter.PreRequestLogFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * 网关
 * @author lihy
 * @date 2018/10/16
 */
@SpringBootApplication(scanBasePackages = "com.lihy.view.zuul")
@EnableEurekaClient
@EnableZuulProxy
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    /*@Bean
    public PreRequestLogFilter getPreRequestLogFilter() {
        return new PreRequestLogFilter();
    }*/
}
