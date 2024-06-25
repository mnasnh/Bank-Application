package com.coding.bankapp.repository.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class AccountDto {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ACCT_ID")
    private UUID id;

    private Long accountNumber;

    @OneToOne(cascade=CascadeType.ALL)
    private BankDto bank;

    private String accountStatus;

    private String accountType;

    private BigDecimal accountBalance;

    @Temporal(TemporalType.TIME)
    private Date createDateTime;

    @Temporal(TemporalType.TIME)
    private Date updateDateTime;
}
