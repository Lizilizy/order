/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.infrastructure.exception;

/**
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */
public class ServiceException extends RuntimeException {
    public ServiceException() {
    }

    public ServiceException(String s) {
        super(s);
    }

    public ServiceException(Throwable t) {
        super(t);
    }

    public ServiceException(String s, Throwable t) {
        super(s, t);
    }
}
