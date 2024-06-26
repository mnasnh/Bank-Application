package com.coding.bankapp.service.impl;

import com.coding.bankapp.dto.Account;
import com.coding.bankapp.enums.AccountStatus;
import com.coding.bankapp.enums.CustomerStatus;
import com.coding.bankapp.exception.BankApiException;
import com.coding.bankapp.exception.EventMessage;
import com.coding.bankapp.repository.AccountRepository;
import com.coding.bankapp.repository.BranchRepository;
import com.coding.bankapp.repository.CustomerAccountRepository;
import com.coding.bankapp.repository.CustomerRepository;
import com.coding.bankapp.repository.entity.AccountEntity;
import com.coding.bankapp.repository.entity.BranchEntity;
import com.coding.bankapp.repository.entity.CustomerAccountEntity;
import com.coding.bankapp.repository.entity.CustomerEntity;
import com.coding.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public Account findByAccountNumber(Long accountNumber) {
        Optional<AccountEntity> accountEntityOpt = accountRepository.findByAccountNumberAndAccountStatus(accountNumber, AccountStatus.ACTIVE.toString());

        if (accountEntityOpt.isPresent()) {
            return Account.from(accountEntityOpt.get());
        }
        return null;
    }

    @Override
    public Account addNewAccount(Account account, Long customerNumber) throws BankApiException {
        Optional<CustomerEntity> customerEntityOpt = customerRepository.findByCustomerNumberAndStatus(customerNumber, CustomerStatus.ENABLED.toString());
        if (!customerEntityOpt.isPresent()) {
            throw new BankApiException(HttpStatusCode.valueOf(400), EventMessage.CUSTOMER_DOES_NOT_EXIST.formatted(customerNumber));
        }
        AccountEntity accountEntity = AccountEntity.from(account);
       Optional<BranchEntity> bankEntityOptional = branchRepository.findById(account.getBranchId());
       if(!bankEntityOptional.isPresent())
       {
           throw new BankApiException(HttpStatusCode.valueOf(400), EventMessage.INVALID_BRANCH_ID.formatted(account.getBranchId()));
       }
       accountEntity.setBranchEntity(bankEntityOptional.get());
       accountEntity.setCreateDateTime(new Date());
       accountEntity.setUpdateDateTime(new Date());
       AccountEntity saved = accountRepository.save(accountEntity);
       CustomerAccountEntity customerAccountEntity = CustomerAccountEntity.builder().accountNumber(saved.getAccountNumber()).customerNumber(customerNumber).build();
       customerAccountRepository.save(customerAccountEntity);
       return Account.from(saved);
    }

    @Override
    public String closeAccount(Long accountNumber) throws BankApiException {
        Optional<AccountEntity> accountEntityOpt = accountRepository.findByAccountNumberAndAccountStatus(accountNumber, AccountStatus.ACTIVE.toString());
        if(!accountEntityOpt.isPresent())
        {
            throw new BankApiException(HttpStatusCode.valueOf(400), EventMessage.ACCOUNT_DOES_NOT_EXIST.formatted(accountNumber));
        }
        AccountEntity accountEntity = accountEntityOpt.get();
        accountEntity.setAccountStatus(AccountStatus.CLOSED.toString());
        accountEntity.setUpdateDateTime(new Date());
        accountRepository.save(accountEntity);
        return "Success: Account Closed";
    }

}
