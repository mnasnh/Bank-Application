package com.coding.bankapp.service.impl;

import com.coding.bankapp.dto.Customer;
import com.coding.bankapp.dto.CustomerUpdateRequest;
import com.coding.bankapp.enums.AccountStatus;
import com.coding.bankapp.enums.CustomerStatus;
import com.coding.bankapp.exception.BankApiException;
import com.coding.bankapp.exception.EventMessage;
import com.coding.bankapp.repository.AccountRepository;
import com.coding.bankapp.repository.CustomerAccountRepository;
import com.coding.bankapp.repository.CustomerRepository;
import com.coding.bankapp.repository.entity.AccountEntity;
import com.coding.bankapp.repository.entity.CustomerAccountEntity;
import com.coding.bankapp.repository.entity.CustomerEntity;
import com.coding.bankapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerAccountRepository customerAccountRepository;
    @Override
    public List<Customer> findAll() {
        List<Customer> allCustomerDetails = new ArrayList<>();

        Iterable<CustomerEntity> customerList = customerRepository.findAllByStatus(CustomerStatus.ENABLED.toString()).get();

        customerList.forEach(customerEntity -> {
            allCustomerDetails.add(Customer.from(customerEntity));
        });

        return allCustomerDetails;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        CustomerEntity customerEntity = CustomerEntity.from(customer);
        customerEntity.setCreateDateTime(new Date());
        customerEntity.setUpdateDateTime(new Date());
        CustomerEntity saved = customerRepository.save(customerEntity);
        return Customer.from(saved);
    }

    @Override
    public Customer findBy(Long customerNumber) {
        Optional<CustomerEntity> customerEntityOpt = customerRepository.findByCustomerNumberAndStatus(customerNumber, CustomerStatus.ENABLED.toString());

        if(customerEntityOpt.isPresent()) {
            return Customer.from(customerEntityOpt.get());
        }

        return null;
    }

    @Override
    public Customer updateCustomer(CustomerUpdateRequest customerUpdateRequest, Long customerNumber) {
        return null;
    }

    @Override
    public String deleteCustomer(Long customerNumber) throws BankApiException {
           Optional<CustomerEntity> customerEntityOptional = customerRepository.findByCustomerNumberAndStatus(customerNumber, CustomerStatus.ENABLED.toString());
            if(!customerEntityOptional.isPresent())
            {
                throw new BankApiException(HttpStatusCode.valueOf(400),EventMessage.CUSTOMER_DOES_NOT_EXIST.formatted(customerNumber));
            }
            // Disable customer
            CustomerEntity managedCustomerEntity = customerEntityOptional.get();
            managedCustomerEntity.setStatus(CustomerStatus.DISABLED.toString());
            managedCustomerEntity.setUpdateDateTime(new Date());
            customerRepository.save(managedCustomerEntity);
            // close  existing account
            Optional<List<CustomerAccountEntity>> customerAccountEntityOptional = customerAccountRepository.findAllByCustomerNumber(customerNumber);
            if (customerAccountEntityOptional.isPresent() && !customerAccountEntityOptional.get().isEmpty()) {
                List<CustomerAccountEntity> entities = customerAccountEntityOptional.get();
                entities.forEach(customerAccountEntity -> {
                    Optional<AccountEntity> accountEntityOpt = accountRepository.findByAccountNumberAndAccountStatus(customerAccountEntity.getAccountNumber(), AccountStatus.ACTIVE.toString());
                       if(accountEntityOpt.isPresent()) {
                           AccountEntity accountEntity = accountEntityOpt.get();
                           accountEntity.setAccountStatus(AccountStatus.CLOSED.toString());
                           accountEntity.setUpdateDateTime(new Date());
                           accountRepository.save(accountEntity);
                       }
                });

            }
        // delete mapping of customer and account
            customerAccountRepository.deleteByCustomerNumber(customerNumber);
            return "Success: Customer deactivated.";
    }
}
