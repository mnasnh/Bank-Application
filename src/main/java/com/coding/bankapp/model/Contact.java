package com.coding.bankapp.model;

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
}
