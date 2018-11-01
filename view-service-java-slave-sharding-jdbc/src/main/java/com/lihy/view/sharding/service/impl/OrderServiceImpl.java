package com.lihy.view.sharding.service.impl;

import com.lihy.view.common.constant.SystemConstant;
import com.lihy.view.common.entity.Order;
import com.lihy.view.common.util.ResponseResult;
import com.lihy.view.sharding.mapper.OrderMapper;
import com.lihy.view.sharding.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lihy
 * @date 2018/10/25
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public ResponseResult<Void> insertOrder(Order order) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        int i =  orderMapper.insertOrder(order);
        if (i > 0) {
            responseResult.setResponseCode(SystemConstant.SUCCESS.getCode());
            responseResult.setResponseMsg("插入成功！");
        } else {
            responseResult.setResponseCode(SystemConstant.ERROR.getCode());
            responseResult.setResponseMsg("插入失败！");
        }
        return responseResult;
    }

    @Override
    public ResponseResult<List<Order>> selectOrderByUserId(Integer userId) {
        ResponseResult<List<Order>> responseResult = new ResponseResult<>();
        List<Order> orderList = orderMapper.selectOrderByUserId(userId);
        responseResult.setData(orderList);
        responseResult.setResponseCode(SystemConstant.SUCCESS.getCode());
        responseResult.setResponseMsg(SystemConstant.SUCCESS.getName());
        return responseResult;
    }
}
