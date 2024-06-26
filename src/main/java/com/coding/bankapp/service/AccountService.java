package com.coding.bankapp.service;

import com.coding.bankapp.dto.Account;
import com.coding.bankapp.exception.BankApiException;

public interface AccountService {
    Account findByAccountNumber(Long accountNumber);

    Account addNewAccount(Account accountInformation, Long customerNumber) throws BankApiException;

    String closeAccount(Long accountNumber) throws BankApiException;

}
