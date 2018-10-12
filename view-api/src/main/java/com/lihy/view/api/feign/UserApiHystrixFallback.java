package com.lihy.view.api.feign;

import com.lihy.view.common.constant.SystemConstant;
import com.lihy.view.common.entity.User;
import com.lihy.view.common.util.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author lihy
 * @date 2018/04/17
 */
@Component
public class UserApiHystrixFallback implements UserApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserApiHystrixFallback.class);
    @Override
    public ResponseResult<User> getUserInfoByUserId(String userId) {
        LOGGER.error("---------------根据用户guid获取用户信息降级服务start---------------");
        ResponseResult<User> responseResult = new ResponseResult<>();
        responseResult.setResponseCode(SystemConstant.SERVICE_IS_UNABLE.getCode());
        responseResult.setResponseMsg(SystemConstant.SERVICE_IS_UNABLE.getName());
        LOGGER.error("---------------根据用户guid获取用户信息降级服务end---------------");
        return responseResult;
    }

    @Override
    public ResponseResult<Void> doRegister(User user) {
        LOGGER.error("---------------用户注册降级服务start---------------");
        ResponseResult<Void> responseResult = new ResponseResult<>();
        responseResult.setResponseCode(SystemConstant.SERVICE_IS_UNABLE.getCode());
        responseResult.setResponseMsg(SystemConstant.SERVICE_IS_UNABLE.getName());
        LOGGER.error("---------------用户注册降级服务end---------------");
        return responseResult;
    }

    @Override
    public ResponseResult<Void> doLogin(User user) {
        LOGGER.error("---------------用户登录降级服务start---------------");
        ResponseResult<Void> responseResult = new ResponseResult<>();
        responseResult.setResponseCode(SystemConstant.SERVICE_IS_UNABLE.getCode());
        responseResult.setResponseMsg(SystemConstant.SERVICE_IS_UNABLE.getName());
        LOGGER.error("---------------用户登录降级服务end---------------");
        return responseResult;
    }
}
