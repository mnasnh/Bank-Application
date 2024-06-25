package com.coding.bankapp.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Bank {
    private String branchName;
    private String sortCode;
    private Address address;
}
