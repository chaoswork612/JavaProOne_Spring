package org.limit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserLimitResponseDto {
    private Long userLimitId;
    private Double dailyLimit;
    private Long userId;
}
