package com.lihy.view.view.controller;

import com.lihy.view.api.feign.UserApi;
import com.lihy.view.common.entity.User;
import com.lihy.view.common.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户类
 * @author lihy
 * @date 2018/04/16
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserApi userApi;

    @GetMapping("/getUserInfoByUserId/{userId}")
    public ResponseResult<User> getUserInfoByUserId(@PathVariable String userId) {
        return userApi.getUserInfoByUserId(userId);
    }
}
