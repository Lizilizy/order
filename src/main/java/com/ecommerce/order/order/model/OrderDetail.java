/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.order.model;

import java.util.List;

import com.ecommerce.order.infrastructure.exception.ServiceException;
import com.ecommerce.order.infrastructure.utils.GsonUtils;

import lombok.Builder;
import lombok.Value;

/**
 * 订单详情
 *
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-25
 */
@Value
@Builder
public class OrderDetail {
    /**
     * 订单详情
     */
    private List<OrderItem> orderItems;
    /**
     * 收货地址
     */
    private Address address;

    public static OrderDetail createfromJson(String orderDetail) {
        try {
            return GsonUtils.fromJson(orderDetail, OrderDetail.class);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public static OrderDetail of(List<OrderItem> orderItems, Address address) {
        return OrderDetail.builder()
                .orderItems(orderItems)
                .address(address)
                .build();
    }

}
