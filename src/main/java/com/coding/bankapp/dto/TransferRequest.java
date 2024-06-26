package com.coding.bankapp.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransferRequest {
    private Long fromAccountNumber;
    private Long toAccountNumber;
    private BigDecimal transferAmount;
    private Long customerNumber;
}
