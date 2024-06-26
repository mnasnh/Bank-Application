package com.coding.bankapp.service;

import com.coding.bankapp.dto.Customer;
import com.coding.bankapp.dto.CustomerUpdateRequest;
import com.coding.bankapp.exception.BankApiException;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Customer addCustomer(Customer customer);

    Customer findBy(Long customerNumber);

    Customer updateCustomer(CustomerUpdateRequest customerUpdateRequest, Long customerNumber);

    String deleteCustomer(Long customerNumber) throws BankApiException;
}
