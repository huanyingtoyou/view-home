package com.lihy.view.user.controller;

import com.lihy.view.common.entity.User;
import com.lihy.view.common.util.ResponseResult;
import com.lihy.view.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息controller
 * @author lihy
 * @date 2018/04/17
 */
@RestController
@RequestMapping("/user")
public class UserController {
    //日志
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 根据主键id（用户名）获取用户信息
     * @param id
     * @return
     */
    @GetMapping("/userInfo")
    public ResponseResult<User> getUserInfo(String id) {
        ResponseResult<User> responseResult = userService.getUserInfo(id);
        return responseResult;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ResponseResult<Void> addUser(@RequestBody User user) {
        ResponseResult<Void> responseResult = userService.addUser(user);
        return responseResult;
    }
}
