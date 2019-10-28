/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.infrastructure;

import static com.ecommerce.order.infrastructure.common.CommonResponse.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.order.infrastructure.exception.BaseException;
import com.ecommerce.order.infrastructure.exception.ErrorCode;
import com.ecommerce.order.infrastructure.exception.ServiceException;
import com.ecommerce.order.infrastructure.exception.SystemErrorCode;
import com.ecommerce.order.infrastructure.exception.UnexpectedException;
import com.google.common.base.Strings;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public String resolveBaseException(BaseException ex) {
        ErrorCode errorCode = ex.getError();
        String message = ex.getMessage();
        if (Strings.isNullOrEmpty(message)) {
            message = "未知错误";
        }
        return error(errorCode.getStatus(), message);
    }

    /**
     * 系统异常，统一返回“系统繁忙”
     * 不给用户看到
     */
    @ResponseBody
    @ExceptionHandler(UnexpectedException.class)
    public String resolveUnexpectedException(UnexpectedException ex) {
        // 打印异常栈
        log.error("[UnexpectedException]:{}", ex.getMessage(), ex);
        // 直接返回 系统繁忙
        return error(SystemErrorCode.UNEXPECTED.getStatus(), SystemErrorCode.UNEXPECTED.getMessage());
    }

    /**
     * 正常业务异常提示
     * 用户可以看到
     */
    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public String resolveUnexpectedException(ServiceException ex) {
        String message = ex.getMessage();
        if (Strings.isNullOrEmpty(message)) {
            message = "未知错误";
        }
        return error(SystemErrorCode.BUSINESS_SERVICE.getStatus(), message);
    }

    /**
     * 其他异常
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String resolveException(Exception ex) {
        // 打印异常栈
        log.error("[System Exception]:{}", ex.getMessage(), ex);
        // 直接返回 系统繁忙
        return error(SystemErrorCode.SYSTEM.getStatus(), "系统繁忙");
    }

}
