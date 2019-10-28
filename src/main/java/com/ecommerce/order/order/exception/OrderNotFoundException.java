package com.ecommerce.order.order.exception;

import static com.ecommerce.order.order.model.OrderErrorCode.ORDER_NOT_FOUND;
import static com.google.common.collect.ImmutableMap.of;

import com.ecommerce.order.infrastructure.exception.BaseException;

/**
 * order not found exception
 */
public class OrderNotFoundException extends BaseException {
    public OrderNotFoundException(String orderId) {
        super(ORDER_NOT_FOUND, of("orderId", orderId));
    }
}
