package com.coding.bankapp.repository.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class ContactDto {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="CONTACT_ID")
    private UUID id;

    private String emailId;

    private String homePhone;

    private String workPhone;
}
