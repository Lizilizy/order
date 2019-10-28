package com.ecommerce.order.product.model;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.order.order.model.OrderItem;

import lombok.Value;

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
