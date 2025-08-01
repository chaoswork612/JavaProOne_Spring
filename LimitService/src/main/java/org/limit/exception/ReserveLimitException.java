package org.limit.exception;

public class ReserveLimitException extends RuntimeException {
    public ReserveLimitException(String message) {
        super(message);
    }
}
