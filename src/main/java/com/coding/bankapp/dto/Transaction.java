package com.coding.bankapp.dto;

import com.coding.bankapp.enums.TransactionType;
import com.coding.bankapp.repository.entity.TransactionEntity;
import java.util.Date;
import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {
    private String transactionId;
    private TransactionType type;
    private BigDecimal amount;
    private Long accountNumber;
    private Date transactionDate;

    public static Transaction from(TransactionEntity transactionEntity) {
        return Transaction.builder().transactionId(String.valueOf(transactionEntity.getId())).
                type(TransactionType.valueOf(transactionEntity.getTransactionType())).amount(transactionEntity.getTransactionAmount())
                .accountNumber(transactionEntity.getAccountNumber()).transactionDate(transactionEntity.getTransactionDate()).build();
    }
}
