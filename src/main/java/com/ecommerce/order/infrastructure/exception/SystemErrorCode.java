package com.ecommerce.order.infrastructure.exception;

public enum SystemErrorCode implements ErrorCode {

    /**
     * 兜底的系统异常
     */
    SYSTEM(1000, "系统异常"),

    /**
     * 预期不会发生，实际发生的异常
     */
    UNEXPECTED(1001, "系统繁忙"),

    /**
     * 业务服务异常，异常信息可以直接返回给 调用方
     */
    BUSINESS_SERVICE(1002, "");

    private int status;
    private String message;

    SystemErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return this.name();
    }
}
