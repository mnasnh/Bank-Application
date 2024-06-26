package com.coding.bankapp.repository;

import com.coding.bankapp.repository.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    Optional<List<CustomerEntity>> findAllByStatus(String status);

    Optional<CustomerEntity> findByCustomerNumberAndStatus(Long customerNumber, String status);
}
