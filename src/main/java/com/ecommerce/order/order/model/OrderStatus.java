package com.ecommerce.order.order.model;

/**
 * 订单状态
 */
public enum OrderStatus {
    /**
     * 已创建
     */
    CREATED,
    /**
     * 已付款
     */
    PAID,
    /**
     * 已发货
     */
    DELIVERED,
    /**
     * 已收货
     */
    RECEIVED
}
