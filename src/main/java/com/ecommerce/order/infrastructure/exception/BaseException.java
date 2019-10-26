/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.infrastructure.exception;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;

import org.apache.commons.collections4.MapUtils;

/**
 * base exception class for all business exception
 *
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */
public abstract class BaseException extends RuntimeException {

    private final ErrorCode error;
    private final Map<String, Object> data = newHashMap();

    protected BaseException(ErrorCode error, Map<String, Object> data) {
        super(format(error.getCode(), error.getMessage(), data));
        this.error = error;
        if (!MapUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    protected BaseException(ErrorCode code, Map<String, Object> data, Throwable cause) {
        super(format(code.toString(), code.getMessage(), data), cause);
        this.error = code;
        if (!MapUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    private static String format(String code, String message, Map<String, Object> data) {
        return String.format("[%s]%s:%s.", code, message, MapUtils.isEmpty(data) ? "" : data.toString());
    }

    public ErrorCode getError() {
        return error;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
