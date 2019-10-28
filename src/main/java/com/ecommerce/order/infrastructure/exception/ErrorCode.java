package com.ecommerce.order.infrastructure.exception;

/**
 * to be subclassed
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
