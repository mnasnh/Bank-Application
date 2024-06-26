package com.coding.bankapp.dto;

import com.coding.bankapp.enums.AccountStatus;
import com.coding.bankapp.enums.AccountType;
import com.coding.bankapp.repository.entity.AccountEntity;
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
    private Long branchId;
    private AccountType accountType;
    private Date openingDate;

    public static Account from(AccountEntity accountEntity) {
        return Account.builder().accountNumber(accountEntity.getAccountNumber()).
                accountStatus(AccountStatus.valueOf(accountEntity.getAccountStatus())).accountType(AccountType.valueOf(accountEntity.getAccountType()))
                .balance(accountEntity.getAccountBalance()).branchId(accountEntity.getBranchEntity().getId()).openingDate(accountEntity.getCreateDateTime()).build();
    }
}
