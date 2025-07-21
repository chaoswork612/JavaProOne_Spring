package org.limit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestoreLimitResponse {
    private Long limitId;
    private String resultMessage;
}
