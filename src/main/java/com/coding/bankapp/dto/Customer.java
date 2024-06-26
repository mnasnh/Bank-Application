package com.coding.bankapp.dto;

import com.coding.bankapp.enums.CustomerStatus;
import com.coding.bankapp.repository.entity.CustomerEntity;
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

    public static Customer from(CustomerEntity customerEntity) {
        return Customer.builder().firstName(customerEntity.getFirstName()).middleName(customerEntity.getMiddleName())
                .lastName(customerEntity.getLastName()).customerNumber(customerEntity.getCustomerNumber())
                .status(CustomerStatus.valueOf(customerEntity.getStatus())).address(Address.from(customerEntity.getCustomerAddress()))
                .contact(Contact.from(customerEntity.getContact())).build();
    }
}
