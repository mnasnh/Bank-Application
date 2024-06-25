package com.coding.bankapp.repository;

import com.coding.bankapp.repository.dto.AccountDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<AccountDto, Long> {
    Optional<AccountDto> findByAccountNumber(Long accountNumber);
}
