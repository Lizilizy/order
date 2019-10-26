/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.infrastructure.exception;

/**
 * Unexpected Exception
 *
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */
public class UnexpectedException extends RuntimeException {
    public UnexpectedException() {
    }

    public UnexpectedException(String s) {
        super(s);
    }

    public UnexpectedException(Throwable t) {
        super(t);
    }

    public UnexpectedException(String s, Throwable t) {
        super(s, t);
    }
}
