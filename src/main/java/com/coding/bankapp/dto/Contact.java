package com.coding.bankapp.dto;

import com.coding.bankapp.repository.entity.ContactEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Contact {
    private String workPhone;
    private String homePhone;
    private String emailId;

    public static Contact from(ContactEntity contactEntity)
    {
        return Contact.builder().emailId(contactEntity.getEmailId())
                .homePhone(contactEntity.getHomePhone()).workPhone(contactEntity.getWorkPhone()).build();
    }
}
