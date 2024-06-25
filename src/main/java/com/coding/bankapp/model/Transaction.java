package com.coding.bankapp.model;

import com.coding.bankapp.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {
    private String transactionId;
    private String customerNumber;
    private TransactionType type;
    private BigDecimal amount;
    private String method;
    private Long accountNumber;
}
