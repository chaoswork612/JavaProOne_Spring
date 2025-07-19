package org.example.dto;

import org.example.model.User;

import java.math.BigDecimal;
import java.math.BigInteger;

public record GetProductDto (Long id, BigInteger account_number, BigDecimal balance, String productType, User user) {
}
