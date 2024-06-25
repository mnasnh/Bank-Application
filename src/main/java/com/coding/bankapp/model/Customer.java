package com.coding.bankapp.model;

import com.coding.bankapp.enums.CustomerStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer {
    private String firstName;

    private String lastName;

    private String middleName;

    private Long customerNumber;

    private CustomerStatus status;

    private Address address;

    private Contact contact;

}
