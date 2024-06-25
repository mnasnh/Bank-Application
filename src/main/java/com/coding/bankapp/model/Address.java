package com.coding.bankapp.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postCode;
    private String country;
}
