package com.lihy.view.user.test;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.lihy.view.common.entity.User;
import com.lihy.view.common.util.ResponseResult;
import com.lihy.view.user.application.UserServiceApplication;
import com.lihy.view.user.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 用于user服务的单元测试
 * @author lihy
 * @date 2018/09/25
 */
//@RunWith(value = Parameterized.class)
@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JunitTest {

    @Autowired
    private UserService userService;

    /*private String username;
    private String password;

    public JunitTest(String username, String password) {
        this.username = username;
        this.password = password;
    }*/

    @Before
    public void setUp() throws Exception {
        System.out.println("我是before");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("我是after");
    }

    /*@Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new String[][]{{"用户名1", "123456"},{"用户名2", "123456"}});
    }*/

    @Test
    public void test() throws Exception {
        this.doRegister();
    }

    public void getUserInfoByUserId() {
        ResponseResult<User> responseResult = userService.getUserInfoByUserId("6");
        System.out.println(JSONObject.toJSONString(responseResult.getData()));
    }

    public void doRegister() {
        User user = new User();
        user.setUsername("哟用户");
        user.setPassword("67890");
        userService.doRegister(user);
    }

    public void doLogin() {
        User user = new User();
        user.setUsername("测试名");
        user.setPassword("123456");
        userService.doLogin(user);
    }
}
