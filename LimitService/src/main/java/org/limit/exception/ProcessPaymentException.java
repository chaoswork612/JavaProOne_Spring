package org.limit.exception;

public class ProcessPaymentException extends RuntimeException {
    public ProcessPaymentException(String message) {
        super(message);
    }
}
