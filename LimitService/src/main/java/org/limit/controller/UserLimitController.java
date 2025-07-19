package org.limit.controller;

import lombok.RequiredArgsConstructor;
import org.limit.dto.ProcessPaymentRequestDto;
import org.limit.dto.ProcessPaymentResponseDto;
import org.limit.dto.UserLimitResponseDto;
import org.limit.service.UserLimitService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/limits")
public class UserLimitController {

    private final UserLimitService userLimitService;

    @GetMapping("/user/{userId}")
    public UserLimitResponseDto getLimit(@PathVariable Long userId) {
        return userLimitService.getCurrentLimit(userId);
    }

    @PostMapping("/process-payment")
    public ProcessPaymentResponseDto processPayment(@RequestBody ProcessPaymentRequestDto paymentRequestDto) {
        return userLimitService.onPaymentProcess(paymentRequestDto);
    }
}
