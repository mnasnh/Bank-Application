package com.coding.bankapp.service;

import com.coding.bankapp.model.Customer;
import com.coding.bankapp.model.CustomerUpdateRequest;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Customer addUCustomer(Customer customer);

    Customer findBy(Long customerNumber);

    Customer updateCustomer(CustomerUpdateRequest customerUpdateRequest, Long customerNumber);

    int deleteCustomer(Long customerNumber) ;
}
