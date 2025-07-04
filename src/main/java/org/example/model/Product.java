package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@Table(name = "products")
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_number")
    private BigInteger accountNumber;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "product_type")
    private String productType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
