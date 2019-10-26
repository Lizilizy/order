/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.order.model;

/**
 * 订单状态
 *
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-25
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
