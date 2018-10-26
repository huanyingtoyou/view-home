package com.lihy.view.sharding.test;

import com.alibaba.fastjson.JSONObject;
import com.lihy.view.common.entity.Order;
import com.lihy.view.common.util.ResponseResult;
import com.lihy.view.sharding.application.ShardingServiceJavaYmlApplication;
import com.lihy.view.sharding.service.OrderService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 用于user服务的单元测试
 * @author lihy
 * @date 2018/09/25
 */
//@RunWith(value = Parameterized.class)
@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShardingServiceJavaYmlApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JunitTest {

    @Autowired
    private OrderService orderService;

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
        this.insertOrder();
    }

    public void selectOrderByUserId() {
        ResponseResult<List<Order>> responseResult = orderService.selectOrderByUserId(6);
        System.out.println(JSONObject.toJSONString(responseResult.getData()));
    }

    public void insertOrder() {
        Order order = new Order();
        order.setOrderId((long)1);
        order.setUserId(2);
        order.setStatus("可用");
        orderService.insertOrder(order);
    }
}
