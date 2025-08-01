package org.limit.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "user_limits")
@ToString
@NoArgsConstructor
public class UserLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "daily_limit")
    private Double dailyLimit;

    @Column(name = "user_id")
    private Long userId;

    public UserLimit(Double dailyLimit, Long userId) {
        this.dailyLimit = dailyLimit;
        this.userId = userId;
    }
}
