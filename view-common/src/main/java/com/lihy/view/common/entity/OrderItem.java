package com.lihy.view.common.entity;

/**
 * @author lihy
 * @date 2018/10/25
 */
public class OrderItem {
    private Long orderItemId;

    private Long orderId;

    private Integer userId;

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
