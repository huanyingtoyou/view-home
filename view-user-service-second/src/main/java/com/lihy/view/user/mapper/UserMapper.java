package com.lihy.view.user.mapper;

import com.lihy.view.common.entity.User;

/**
 * @author lihy
 * @date 2018/04/17
 */
public interface UserMapper {
    /**
     * 根据用户userId获取用户信息
     * @param userId
     * @return
     */
    User getUserInfoByUserId(String userId);

    /**
     * 用户注册
     * @param user
     * @return
     */
    int doRegister(User user);

    /**
     * 根据用户名获取用户信息
     * @return
     */
    User getUserInfoByUsername(User user);
}
