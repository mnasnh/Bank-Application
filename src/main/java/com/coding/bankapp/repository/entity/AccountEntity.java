package com.coding.bankapp.repository.entity;

import com.coding.bankapp.dto.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class AccountEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ACCT_ID")
    private Long id;

    private Long accountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankEntity.branchId")
    private BranchEntity branchEntity;

    private String accountStatus;

    private String accountType;

    private BigDecimal accountBalance;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDateTime;

    public static AccountEntity from(Account account) {
        return AccountEntity.builder().accountNumber(account.getAccountNumber())
                .accountBalance(account.getBalance()).accountStatus(account.getAccountStatus().toString())
                .accountType(account.getAccountType().toString()).build();
    }
}
