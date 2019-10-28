package com.ecommerce.order.order.exception;

import static com.ecommerce.order.order.model.OrderErrorCode.PRODUCT_NOT_IN_ORDER;
import static com.google.common.collect.ImmutableMap.of;

import com.ecommerce.order.infrastructure.exception.BaseException;

/**
 * product not in order Exception
 */
public class ProductNotInOrderException extends BaseException {
    public ProductNotInOrderException(String productId, String orderId) {
        super(PRODUCT_NOT_IN_ORDER, of("productId", productId, "orderId", orderId));
    }
}
