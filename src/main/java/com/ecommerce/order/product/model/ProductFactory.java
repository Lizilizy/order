package com.ecommerce.order.product.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class ProductFactory {

    /**
     * 这里的产品ID 由人工录入
     */
    public Product create(String productId, String productName, int stock, BigDecimal price, String sellerId) {
        return Product.create(productId, productName, stock, price, sellerId);
    }
}
