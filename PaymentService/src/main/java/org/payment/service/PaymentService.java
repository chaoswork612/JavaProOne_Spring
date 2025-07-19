package org.payment.service;

import lombok.RequiredArgsConstructor;
import org.payment.dto.ExecutePaymentResponseDto;
import org.payment.dto.GetProductIntegrationResponseDto;
import org.payment.exception.PaymentException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final ProductIntegrationService productIntegrationService;

    public ExecutePaymentResponseDto executePayment(Long productId, BigDecimal amount) throws PaymentException {
        GetProductIntegrationResponseDto product = productIntegrationService.getProductById(productId);
        if (product == null || product.getBalance().compareTo(amount) < 0) {
            throw new PaymentException("Product not found or insufficient funds");
        }

        return new ExecutePaymentResponseDto(Math.abs(new Random().nextLong()), "Payment successfully executed");
    }
}
