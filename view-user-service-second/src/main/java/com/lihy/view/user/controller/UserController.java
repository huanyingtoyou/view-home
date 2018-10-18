package com.lihy.view.user.controller;

import com.lihy.view.common.entity.User;
import com.lihy.view.common.util.ResponseResult;
import com.lihy.view.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息controller
 * @author lihy
 * @date 2018/04/17
 */
@RefreshScope
@RestController
@RequestMapping("/user")
@Api(value = "用户操作控制器", description = "获取用户信息以及登录注册等", produces = MediaType.APPLICATION_JSON_VALUE)
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
    @GetMapping(value = "/getUserInfoByUserId/{userId}")
    @ApiOperation(value = "获取用户信息", notes = "根据用户userId获取用户信息")
    public ResponseResult<User> getUserInfoByUserId(@PathVariable String userId) {
        ResponseResult<User> responseResult = userService.getUserInfoByUserId(userId);
        return responseResult;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "用户注册")
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
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public ResponseResult<Void> login(@RequestBody User user) {
        ResponseResult<Void> responseResult = userService.doLogin(user);
        return responseResult;
    }

    @Value("${profile}")
    private String profile;

    @GetMapping("/getName")
    public String getName() {
        return this.profile;
    }
}
