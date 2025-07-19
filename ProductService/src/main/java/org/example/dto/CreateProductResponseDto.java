package org.example.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public record CreateProductResponseDto(Long productId,
        BigInteger accountNumber,
        String productType,
        BigDecimal balance,
        Long userId
) {

}
