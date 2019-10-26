/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.order.model;

import org.springframework.stereotype.Component;

import com.ecommerce.order.infrastructure.utils.id.UuidGenerator;

/**
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */
@Component
public class OrderIdGenerator {
    public String generate() {
        // 订单ID，应该遵循一定的递增规律，需要考虑高并发情况下订单ID的生成
        return UuidGenerator.newUuid();

        // Twitter的snowflake算法
        //        SnowFlake snowFlake = new SnowFlake(1, 1);
        //        return "DD" + snowFlake.nextId();
    }
}
