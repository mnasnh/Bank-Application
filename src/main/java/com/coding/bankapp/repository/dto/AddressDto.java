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
public class AddressDto {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ADDR_ID")

    private UUID id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postCode;
    private String country;
}
