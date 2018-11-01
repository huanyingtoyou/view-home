package com.lihy.view.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihy
 * @date 2018/04/17
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    public static void main(String[] args) {
        int i = 1;
        i = i++;
        System.out.println(i);
        //System.out.println(i++);
        System.out.println(++i);
    }
}
