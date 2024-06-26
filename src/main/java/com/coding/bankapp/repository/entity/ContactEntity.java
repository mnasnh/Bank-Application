package com.coding.bankapp.repository.entity;

import com.coding.bankapp.dto.Contact;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTACT_ID")
    private Long id;

    private String emailId;

    private String homePhone;

    private String workPhone;

    public static ContactEntity from(Contact contact) {
        return ContactEntity.builder().emailId(contact.getEmailId())
                .homePhone(contact.getHomePhone()).workPhone(contact.getWorkPhone()).build();
    }
}
