package com.coding.bankapp.repository;

import com.coding.bankapp.repository.entity.BranchEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends CrudRepository<BranchEntity, Long> {

}
