package com.lihy.view.api.feign;

import com.lihy.view.common.entity.User;
import com.lihy.view.common.util.ResponseResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 用户服务api
 * 采用feign方式
 * @author lihy
 * @date 2018/04/17
 */
@FeignClient(name = "view-user-service", path = "/user", fallback = UserApiHystrixFallback.class)
public interface UserApi {

    /**
     * 根据用户userId获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getUserInfoByUserId", method = RequestMethod.GET)
    ResponseResult<User> getUserInfoByUserId(@RequestParam(value = "userId") String userId);

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    ResponseResult<Void> doRegister(User user);
    /**
     * 用户登录
     * @param user
     * username 用户名
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    ResponseResult<Void> doLogin(User user);
}
