/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.infrastructure.common;

/**
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */

import com.ecommerce.order.infrastructure.utils.GsonUtils;

import lombok.Data;

/**
 * Common response.
 *
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-25
 */
@Data
public class CommonResponse {
    /**
     * API 调用是否成功
     */
    private boolean success;
    /**
     * 错误码
     */
    private int errorCode;
    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 数据
     */
    private Object result;

    public static String error(int errorCode, String errorMessage) {
        CommonResponse response = new CommonResponse();
        response.setSuccess(false);
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);
        return GsonUtils.toJson(response);
    }

    public static String success(Object result) {
        CommonResponse response = new CommonResponse();
        response.setSuccess(true);
        response.setResult(result);
        return GsonUtils.toJson(response);
    }

    public static String success() {
        return success(null);
    }
}
