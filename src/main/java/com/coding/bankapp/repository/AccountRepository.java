package com.coding.bankapp.repository;

import com.coding.bankapp.repository.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByAccountNumberAndAccountStatus(Long accountNumber, String status);
}
