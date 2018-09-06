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
     * 根据用户userId获取用户信息
     * @param userId
     * @return
     */
    @PostMapping("/getUserInfoByGuid")
    public ResponseResult<User> getUserInfoByUserId(@RequestParam String userId) {
        ResponseResult<User> responseResult = userService.getUserInfoByUserId(userId);
        return responseResult;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ResponseResult<Void> register(@RequestBody User user) {
        ResponseResult<Void> responseResult = userService.doRegister(user);
        return responseResult;
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ResponseResult<Void> login(@RequestBody User user) {
        ResponseResult<Void> responseResult = userService.doLogin(user);
        return responseResult;
    }
}
