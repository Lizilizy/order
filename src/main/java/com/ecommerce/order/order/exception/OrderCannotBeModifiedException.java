/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.order.exception;

import static com.ecommerce.order.order.model.OrderErrorCode.ORDER_CANNOT_BE_MODIFIED;

import com.ecommerce.order.infrastructure.exception.BaseException;
import com.google.common.collect.ImmutableMap;

/**
 * Order Cannot Be Modified Exception
 *
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */
public class OrderCannotBeModifiedException extends BaseException {

    public OrderCannotBeModifiedException(String orderId) {
        super(ORDER_CANNOT_BE_MODIFIED, ImmutableMap.of("orderId", orderId));
    }
}
