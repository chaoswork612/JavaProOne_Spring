package org.limit.dto;

public record ProcessPaymentRequestDto(Long userId, Double amount, Boolean isPaymentSuccessful) {
}
