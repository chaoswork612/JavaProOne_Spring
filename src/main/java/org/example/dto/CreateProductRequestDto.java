package org.example.dto;

import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
public class CreateProductRequestDto {
    private BigInteger accountNumber;
    private BigDecimal balance;
    private String productType;
    private String username;
}