package org.payment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.payment.dto.ExecutePaymentRequestDto;
import org.payment.dto.ExecutePaymentResponseDto;
import org.payment.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/execute")
    public ExecutePaymentResponseDto executePayment(@RequestBody ExecutePaymentRequestDto executePaymentRequestDto) {
        return paymentService.executePayment(executePaymentRequestDto.productId(), executePaymentRequestDto.amount());
    }
}
