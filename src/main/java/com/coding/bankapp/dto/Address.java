package com.coding.bankapp.dto;

import com.coding.bankapp.repository.entity.AddressEntity;
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

    public static Address from(AddressEntity addressEntity)
    {
        return Address.builder().addressLine1(addressEntity.getAddressLine1())
                .addressLine2(addressEntity.getAddressLine2()).city(addressEntity.getCity())
                .postCode(addressEntity.getPostCode()).country(addressEntity.getCountry()).build();
    }
}
