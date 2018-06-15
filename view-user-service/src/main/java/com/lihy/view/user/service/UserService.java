package com.lihy.view.user.service;

import com.lihy.view.api.feign.UserApi;
import com.lihy.view.common.entity.User;
import com.lihy.view.common.util.ResponseResult;

/**
 * @author lihy
 * @date 2018/04/17
 */
public interface UserService extends UserApi {
    /**
     * 用户注册
     * @param user
     * @return
     */
    ResponseResult<Void> addUser(User user);
}
