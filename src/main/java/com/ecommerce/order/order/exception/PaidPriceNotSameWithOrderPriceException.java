package com.ecommerce.order.order.exception;

import static com.ecommerce.order.order.model.OrderErrorCode.PAID_PRICE_NOT_SAME_WITH_ORDER_PRICE;
import static com.google.common.collect.ImmutableMap.of;

import com.ecommerce.order.infrastructure.exception.BaseException;

/**
 * paid price not same with order price exception
 */
public class PaidPriceNotSameWithOrderPriceException extends BaseException {
    public PaidPriceNotSameWithOrderPriceException(String id) {
        super(PAID_PRICE_NOT_SAME_WITH_ORDER_PRICE, of("orderId", id));
    }
}
