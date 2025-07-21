package org.limit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReserveLimitRequest {
    private Long userId;
    Double amount;
    Boolean isPaymentSuccessful;
}
