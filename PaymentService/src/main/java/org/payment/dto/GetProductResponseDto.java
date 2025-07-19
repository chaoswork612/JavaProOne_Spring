package org.payment.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public record GetProductResponseDto(
        Long id,
        BigInteger account_number,
        BigDecimal balance,
        String productType,
        GetUserResponseDto user
) {
}
