/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.product.model;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.order.order.model.OrderItem;

import lombok.Value;

/**
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */
@Value
public class ProductPurchaseItem {
    private String productId;
    private int quantity;

    public static List<ProductPurchaseItem> createProductPurchaseItems(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(it -> new ProductPurchaseItem(it.getProductId(), it.getCount()))
                .collect(Collectors.toList());
    }
}
