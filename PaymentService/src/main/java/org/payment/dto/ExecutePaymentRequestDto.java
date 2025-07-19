package org.payment.dto;

import java.math.BigDecimal;

public record ExecutePaymentRequestDto(Long productId, BigDecimal amount) {
}
