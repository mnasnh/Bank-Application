package com.coding.bankapp.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class TransactionDto {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="TX_ID")
    private UUID id;

    private Long accountNumber;

    @Temporal(TemporalType.TIME)
    private Date transactionDate;

    private String transactionType;

    private BigDecimal transactionAmount;
}
