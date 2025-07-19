package org.limit.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.limit.dto.*;
import org.limit.exception.ProcessPaymentException;
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

    public ProcessPaymentResponseDto onPaymentProcess(ProcessPaymentRequestDto paymentRequestDto) {
        UserLimitResponseDto userLimitsDto = getCurrentLimit(paymentRequestDto.userId());
        userLimitsDto.setDailyLimit(userLimitsDto.getDailyLimit() - paymentRequestDto.amount());
        userLimitRepository.save(Optional.of(new UserLimit()).map(ul -> {
            ul.setId(userLimitsDto.getUserLimitId());
            ul.setDailyLimit(userLimitsDto.getDailyLimit());
            ul.setUserId(userLimitsDto.getUserId());
            return ul;
        }).orElseThrow());
        if (paymentRequestDto.isPaymentSuccessful()) {
            return new ProcessPaymentResponseDto(userLimitsDto.getDailyLimit(), paymentRequestDto.amount());
        } else {
            restoreLimit(paymentRequestDto.userId(), paymentRequestDto.amount());
            throw new ProcessPaymentException("Payment was not successful");
        }
    }

    public void restoreLimit(Long userId, Double amount) {
        UserLimit userLimit = userLimitRepository.findUserLimitByUserId(userId)
                .orElseThrow(EntityNotFoundException::new);
        userLimit.setDailyLimit(userLimit.getDailyLimit() + amount);
        userLimitRepository.save(userLimit);
    }

    private UserLimitResponseDto createUserLimit(Long userId) {
        UserLimit userLimit = userLimitRepository.save(
                userLimitRepository.findUserLimitByUserId(userId).orElse(new UserLimit(10000D, userId))
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
