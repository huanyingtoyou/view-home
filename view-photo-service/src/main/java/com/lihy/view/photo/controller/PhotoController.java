package com.lihy.view.photo.controller;

import com.lihy.view.api.feign.UserApi;
import com.lihy.view.common.entity.User;
import com.lihy.view.common.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private UserApi userApi;

    @GetMapping("/getUserInfoByUserId/{userId}")
    public ResponseResult<User> getUserInfoByUserId(@PathVariable String userId) {
        return userApi.getUserInfoByUserId(userId);
    }
    @Value("${profile}")
    private String profile;

    @GetMapping("/getName")
    public String getName() {
        return this.profile;
    }


}
