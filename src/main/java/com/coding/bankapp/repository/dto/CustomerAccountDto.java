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
public class CustomerAccountDto {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="CUST_ACC_REF_ID")
    private UUID id;

    private Long accountNumber;

    private Long customerNumber;
}
