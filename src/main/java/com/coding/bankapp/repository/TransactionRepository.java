package com.coding.bankapp.repository;

import com.coding.bankapp.repository.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, String> {
     Optional<List<TransactionEntity>> findByAccountNumberOrderByTransactionDateDesc(Long accountNumber);
}
