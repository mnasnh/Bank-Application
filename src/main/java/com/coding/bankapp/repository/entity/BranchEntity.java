package com.coding.bankapp.repository.entity;

import com.coding.bankapp.dto.Branch;
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
public class BranchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BRANCH_ID")
    private Long id;

    private String branchName;

    private String sortCode;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity address;

    public static BranchEntity from(Branch branch) {
        return BranchEntity.builder().address(AddressEntity.from(branch.getAddress())).branchName(branch.getBranchName())
                .sortCode(branch.getSortCode()).build();
    }
}
