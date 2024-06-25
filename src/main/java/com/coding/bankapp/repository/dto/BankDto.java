package com.coding.bankapp.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class BankDto {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="BANK_ID")
    private UUID id;

    private String branchName;

    private String sortCode;

    @OneToOne(cascade=CascadeType.ALL)
    private AddressDto address;

}
