package org.limit.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.limit.dto.*;
import org.limit.exception.ReserveLimitException;
import org.limit.model.UserLimit;
import org.limit.repository.UserLimitRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserLimitService {

    private final UserLimitRepository userLimitRepository;

    public UserLimitResponseDto getCurrentLimit(Long userId) {
        return userLimitRepository.findUserLimitByUserId(userId)
                .map(ul ->
                        new UserLimitResponseDto(ul.getId(), ul.getDailyLimit(), ul.getUserId())
                ).orElseGet(() -> createUserLimit(userId));
    }

    public ReserveLimitResponse reserveLimit(ReserveLimitRequest paymentRequestDto) {
        if (paymentRequestDto.getIsPaymentSuccessful()) {
            UserLimitResponseDto userLimitsDto = getCurrentLimit(paymentRequestDto.getUserId());
            Double newDailyLimit = userLimitsDto.getDailyLimit() - paymentRequestDto.getAmount();
            userLimitsDto.setDailyLimit(newDailyLimit);
            UserLimit userLimit = Optional.of(new UserLimit())
                    .map(ul -> {
                        ul.setId(userLimitsDto.getUserLimitId());
                        ul.setDailyLimit(userLimitsDto.getDailyLimit());
                        ul.setUserId(userLimitsDto.getUserId());
                        return ul;
                    }).orElseThrow();
            userLimitRepository.save(userLimit);
            return new ReserveLimitResponse(
                    userLimitsDto.getUserId(),
                    userLimitsDto.getDailyLimit(),
                    paymentRequestDto.getAmount()
            );
        } else {
            throw new ReserveLimitException("Reserve Limit was not successful");
        }
    }

    public RestoreLimitResponse restoreLimit(RestoreLimitRequest restoreLimitRequest) {
        UserLimit userLimit = userLimitRepository
                .findUserLimitByUserId(restoreLimitRequest.getUserId())
                .orElseThrow(EntityNotFoundException::new);
        userLimit.setDailyLimit(userLimit.getDailyLimit() + restoreLimitRequest.getAmount());
        userLimitRepository.save(userLimit);
        return new RestoreLimitResponse(userLimit.getId(), "Limit restore was successful");
    }

    private UserLimitResponseDto createUserLimit(Long userId) {
        UserLimit userLimit = userLimitRepository.save(
                userLimitRepository
                        .findUserLimitByUserId(userId)
                        .orElse(new UserLimit(10000D, userId))
        );
        return new UserLimitResponseDto(userLimit.getId(), userLimit.getDailyLimit(), userLimit.getUserId());
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void resetLimits() {
        userLimitRepository.findAll().forEach(userLimit -> {
            userLimit.setDailyLimit(10000.00);
            userLimitRepository.save(userLimit);
        });
    }
}
