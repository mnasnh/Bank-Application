package com.coding.bankapp.repository;

import com.coding.bankapp.repository.dto.CustomerAccountDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAccountRepository extends CrudRepository<CustomerAccountDto, String> {
}
