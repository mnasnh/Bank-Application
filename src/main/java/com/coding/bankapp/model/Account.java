package com.coding.bankapp.model;

import com.coding.bankapp.enums.AccountType;
import com.coding.bankapp.enums.AccountStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Account {

    private Long accountNumber;
    private BigDecimal balance;
    private AccountStatus accountStatus;
    private Bank bank;
    private AccountType accountType;
    private Date openingDate;

}
