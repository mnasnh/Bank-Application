package com.coding.bankapp.repository.entity;

import com.coding.bankapp.dto.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class AddressEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ADDR_ID")

    private Long id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postCode;
    private String country;

    public static AddressEntity from(Address address)
    {
        return AddressEntity.builder().addressLine1(address.getAddressLine1()).
                addressLine2(address.getAddressLine2()).city(address.getCity())
                .postCode(address.getPostCode()).country(address.getCountry()).build();
    }
}
