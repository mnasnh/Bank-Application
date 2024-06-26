package com.coding.bankapp.repository;

import com.coding.bankapp.repository.entity.CustomerAccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerAccountRepository extends CrudRepository<CustomerAccountEntity, String> {

    Optional<List<CustomerAccountEntity>> findAllByCustomerNumber(Long customerNumber);

    Optional<CustomerAccountEntity> findByCustomerNumberAndAccountNumber(Long customerNumber, Long accountNumber);

    void deleteByCustomerNumber(Long customerNumber);
}
