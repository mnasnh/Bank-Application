package com.coding.bankapp.service;

import com.coding.bankapp.model.Account;

public interface AccountService {
    Account findByAccountNumber(Long accountNumber);

    Account addNewAccount(Account accountInformation, Long customerNumber);
}
