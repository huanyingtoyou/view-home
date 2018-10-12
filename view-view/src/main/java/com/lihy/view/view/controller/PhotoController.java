package com.lihy.view.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 相册类（照片类）
 * @author lihy
 * @date 2018/04/16
 */
@Controller
@RequestMapping("/photo")
public class PhotoController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
