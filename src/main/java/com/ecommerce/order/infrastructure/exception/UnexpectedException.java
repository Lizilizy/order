package com.ecommerce.order.infrastructure.exception;

/**
 * Unexpected Exception
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
