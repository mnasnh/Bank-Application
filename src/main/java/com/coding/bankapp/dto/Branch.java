package com.coding.bankapp.dto;

import com.coding.bankapp.repository.entity.BranchEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Branch {
    private Long branchId;
    private String branchName;
    private String sortCode;
    private Address address;

    public static Branch from(BranchEntity branchEntity) {
        return Branch.builder().address(Address.from(branchEntity.getAddress())).
                branchName(branchEntity.getBranchName()).sortCode(branchEntity.getSortCode()).branchId(branchEntity.getId()).build();
    }
}
