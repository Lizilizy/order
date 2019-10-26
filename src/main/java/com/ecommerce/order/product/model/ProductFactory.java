/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.product.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

/**
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */
@Component
public class ProductFactory {

    /**
     * 这里的产品ID 由人工录入
     */
    public Product create(String productId, String productName, int stock, BigDecimal price, String sellerId) {
        return Product.create(productId, productName, stock, price, sellerId);
    }
}
