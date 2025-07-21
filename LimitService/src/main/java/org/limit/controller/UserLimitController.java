package org.limit.controller;

import lombok.RequiredArgsConstructor;
import org.limit.dto.*;
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

    @PostMapping("/reserve-limit")
    public ReserveLimitResponse reserveLimit(@RequestBody ReserveLimitRequest paymentRequestDto) {
        return userLimitService.reserveLimit(paymentRequestDto);
    }

    @PostMapping("/restore-limit")
    public RestoreLimitResponse restoreLimit(@RequestBody RestoreLimitRequest restoreLimitRequest) {
        return userLimitService.restoreLimit(restoreLimitRequest);
    }
}
