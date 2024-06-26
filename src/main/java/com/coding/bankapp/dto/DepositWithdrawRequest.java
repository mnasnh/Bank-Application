package com.coding.bankapp.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DepositWithdrawRequest {
    private Long accountNumber;
    private Long customerNumber;
    private BigDecimal amount;
}
