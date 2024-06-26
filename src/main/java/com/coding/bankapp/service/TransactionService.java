package com.coding.bankapp.service;

import com.coding.bankapp.dto.DepositWithdrawRequest;
import com.coding.bankapp.dto.Transaction;
import com.coding.bankapp.dto.TransferRequest;
import com.coding.bankapp.exception.BankApiException;

import java.util.List;

public interface TransactionService {
    String transfer(TransferRequest transferRequest) throws BankApiException;

    String deposit(DepositWithdrawRequest depositWithdrawRequest) throws BankApiException;

    String withdraw(DepositWithdrawRequest depositWithdrawRequest) throws BankApiException;

    List<Transaction> findTransactionsByAccountNumber(Long accountNumber);
}
