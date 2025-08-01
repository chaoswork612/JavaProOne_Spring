package org.limit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReserveLimitResponse {
    private Long userId;
    private Double remainingDailyLimit;
    private Double processedAmount;

}
