/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.product.command;

import java.math.BigDecimal;

import com.ecommerce.order.infrastructure.utils.GsonUtils;

/**
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */
public class Test {
    public static void main(String[] args) {
        CreateProductCommand command = new CreateProductCommand("12345", "华为Mate 3o Pro",
                30000, BigDecimal.valueOf(5000), "seller_1");
        System.out.println(GsonUtils.toJson(command));
    }
}
