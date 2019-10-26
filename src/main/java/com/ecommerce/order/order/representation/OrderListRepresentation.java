/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.order.representation;

import java.math.BigDecimal;
import java.util.Date;

import com.ecommerce.order.order.model.Address;

import lombok.Builder;
import lombok.Data;

/**
 * 订单列表 展示封装类
 *
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-25
 */
@Data
@Builder
public class OrderListRepresentation {
    private String orderId;
    private BigDecimal totalPrice;
    private String status;
    private Address address;
    private Date createdAt;
}
