package com.lihy.view.view.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试类
 * @author lihy
 * @date 2018/04/16
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/sayHello")
    public String sayHello(@RequestParam String name) {
        return "";
    }
}
