package com.coding.bankapp.repository;

import com.coding.bankapp.repository.entity.AccountEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
    /**
     * Find by account number and account status
     * Hold PESSIMISTIC_WRITE lock to prevent other transactions from
     * reading and updating the account balance.
     * @param accountNumber
     * @param status
     * @return
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<AccountEntity> findByAccountNumberAndAccountStatus(Long accountNumber, String status);
}
