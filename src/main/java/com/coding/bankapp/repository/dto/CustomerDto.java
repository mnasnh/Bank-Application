package com.coding.bankapp.repository.dto;

import lombok.*;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDto {
    @Id
    @GeneratedValue
    @Column(name="CUST_ID")
    private UUID id;

    private String firstName;

    private String lastName;

    private String middleName;

    private Long customerNumber;

    private String status;

    @ManyToOne(cascade= CascadeType.ALL)
    private AddressDto customerAddress;

    @OneToOne(cascade=CascadeType.ALL)
    private ContactDto contact;

    @Temporal(TemporalType.TIME)
    private Date createDateTime;

    @Temporal(TemporalType.TIME)
    private Date updateDateTime;
}
