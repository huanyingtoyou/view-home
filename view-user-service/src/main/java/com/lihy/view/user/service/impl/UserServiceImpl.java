package com.lihy.view.user.service.impl;

import com.lihy.view.common.constant.SystemConstant;
import com.lihy.view.common.entity.User;
import com.lihy.view.common.util.ResponseResult;
import com.lihy.view.user.mapper.UserMapper;
import com.lihy.view.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lihy
 * @date 2018/04/17
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据主键id获取用户信息
     * @param id
     * @return
     */
    @Override
    public ResponseResult<User> getUserInfo(String id){
        LOGGER.info("-----------------根据用户名获取用户信息start-----------------");
        ResponseResult<User> responseResult = new ResponseResult<>();
        //判断用户名是否为空
        if (StringUtils.isBlank(id)) {
            responseResult.setResponseCode(SystemConstant.REQ_PARAMS_ERR.getCode());
            responseResult.setResponseMsg(SystemConstant.REQ_PARAMS_ERR.getName());
            return responseResult;
        }
        //根据用户名查询用户
        User user = userMapper.getUserInfo(id);
        if (null == user) {
            responseResult.setResponseCode(SystemConstant.USER_IS_NULL.getCode());
            responseResult.setResponseMsg(SystemConstant.USER_IS_NULL.getName());
            return responseResult;
        }
        responseResult.setData(user);
        responseResult.setResponseCode(SystemConstant.SUCCESS.getCode());
        responseResult.setResponseMsg(SystemConstant.SUCCESS.getName());
        return responseResult;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public ResponseResult<Void> addUser(User user) {
        LOGGER.info("-----------------新增用户start-----------------");
        ResponseResult<Void> responseResult = new ResponseResult<>();
        int isInsert = userMapper.addUser(user);
        if (isInsert > 0) {
            responseResult.setResponseCode(SystemConstant.SUCCESS.getCode());
            responseResult.setResponseMsg(SystemConstant.SUCCESS.getName());
        } else {
            responseResult.setResponseCode(SystemConstant.ERROR.getCode());
            responseResult.setResponseMsg(SystemConstant.ERROR.getName());
        }
        return responseResult;
    }
}
