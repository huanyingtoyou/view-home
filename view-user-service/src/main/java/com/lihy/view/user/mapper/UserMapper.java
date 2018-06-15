package com.lihy.view.user.mapper;

import com.lihy.view.common.entity.User;

/**
 * @author lihy
 * @date 2018/04/17
 */
public interface UserMapper {
    /**
     * 根据主键id获取用户信息
     * @param id
     * @return
     */
    User getUserInfo(String id);

    /**
     * 用户注册
     * @param user
     * @return
     */
    int addUser(User user);
}
