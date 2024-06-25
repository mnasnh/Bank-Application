package com.coding.bankapp.repository;

import com.coding.bankapp.repository.dto.TransactionDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionDto, String> {
     Optional<List<TransactionDto>> findByAccountNumber(Long accountNumber);
}
