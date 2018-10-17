package com.lihy.view.api.feign;

import com.lihy.view.common.constant.SystemConstant;
import com.lihy.view.common.entity.User;
import com.lihy.view.common.util.ResponseResult;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 这里可以看到回退的原因
 * @author lihy
 * @date 2018/10/17
 */
@Component
public class UserApiHystrixFallbackFactory implements FallbackFactory<UserApi> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserApiHystrixFallbackFactory.class);

    @Override
    public UserApi create(Throwable throwable) {
        return new UserApi() {
            @Override
            public ResponseResult<User> getUserInfoByUserId(String userId) {
                LOGGER.error("---------------根据用户guid获取用户信息降级服务start---------------");
                UserApiHystrixFallbackFactory.LOGGER.info("fallback;原因：", throwable);
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
        };
    }
}
