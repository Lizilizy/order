package com.ecommerce.order.order.model;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 订单工厂类
 */
@Component
public class OrderFactory {
    private final OrderIdGenerator idGenerator;

    public OrderFactory(OrderIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Order create(List<OrderItem> items, Address address,String buyerId,String sellerId) {
        String orderId = idGenerator.generate();
        return Order.create(orderId, items, address,buyerId,sellerId);
    }
}
