package org.limit.repository;

import org.limit.model.UserLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLimitRepository extends JpaRepository<UserLimit, Long> {
    Optional<UserLimit> findUserLimitByUserId(Long userId);
    @Modifying
    @Query(value = "UPDATE spring.user_limits SET daily_limit = (:dailyLimit) WHERE user_id IN (:userIds)", nativeQuery = true)
    void updateUserLimits(@Param("dailyLimit") Double dailyLimit, @Param("userIds") List<Long> userIds);
}
