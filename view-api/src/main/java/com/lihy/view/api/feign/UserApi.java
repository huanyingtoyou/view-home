package com.lihy.view.api.feign;

import com.lihy.view.common.entity.User;
import com.lihy.view.common.util.ResponseResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 用户服务api
 * 采用feign方式
 * @author lihy
 * @date 2018/04/17
 */
@FeignClient(name = "view-user-service", path = "/user", fallback = UserApiHystrixFallback.class)
public interface UserApi {

    @GetMapping("/userInfo")
    ResponseResult<User> getUserInfo(String id);
}
