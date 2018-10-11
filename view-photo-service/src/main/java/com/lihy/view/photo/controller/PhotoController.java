package com.lihy.view.photo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihy
 * @date 2018/10/11
 */
@RefreshScope
@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Value("${profile}")
    private String profile;

    @GetMapping("/getName")
    public String getName() {
        return this.profile;
    }
}
