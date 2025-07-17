package org.payment.exception;

public class IntegrationException extends RuntimeException {
    private final String externalMessage;
    public IntegrationException(String message, String externalMessage) {
        super(message);
        this.externalMessage = externalMessage;
    }
}
