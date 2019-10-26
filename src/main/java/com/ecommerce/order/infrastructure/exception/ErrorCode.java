/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.infrastructure.exception;

/**
 * to be subclassed
 *
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */
public interface ErrorCode {
    /**
     * get status
     *
     * @return 状态
     */
    int getStatus();

    /**
     * get code
     *
     * @return 状态码
     */
    String getCode();

    /**
     * get message
     *
     * @return 错误信息
     */
    String getMessage();

}
