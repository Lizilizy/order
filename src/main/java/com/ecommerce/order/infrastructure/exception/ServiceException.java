package com.ecommerce.order.infrastructure.exception;

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
