package com.coding.bankapp.repository.entity;

import com.coding.bankapp.dto.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUST_ID")
    private Long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private Long customerNumber;

    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    private AddressEntity customerAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private ContactEntity contact;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDateTime;

    public static CustomerEntity from(Customer customer) {
        return CustomerEntity.builder().contact(ContactEntity.from(customer.getContact()))
                .customerNumber(customer.getCustomerNumber()).firstName(customer.getFirstName())
                .lastName(customer.getLastName()).middleName(customer.getMiddleName())
                .customerAddress(AddressEntity.from(customer.getAddress())).status(customer.getStatus().toString()).build();
    }
}
