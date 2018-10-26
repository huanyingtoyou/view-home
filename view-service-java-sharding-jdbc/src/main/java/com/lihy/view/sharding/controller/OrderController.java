package com.lihy.view.sharding.controller;

import com.lihy.view.common.entity.Order;
import com.lihy.view.common.util.ResponseResult;
import com.lihy.view.sharding.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lihy
 * @date 2018/10/25
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/insertOrder")
    public ResponseResult<Void> insertOrder(@RequestBody Order order) {
        return orderService.insertOrder(order);
    }
    @GetMapping("/selectOrderByUserId")
    public ResponseResult<List<Order>> selectOrderByUserId(Integer userId) {
        return orderService.selectOrderByUserId(userId);
    }
}
