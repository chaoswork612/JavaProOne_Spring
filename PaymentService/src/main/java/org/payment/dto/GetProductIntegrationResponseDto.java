package org.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class GetProductIntegrationResponseDto {
    private Long id;
    private String productType;
    private BigDecimal balance;
}
