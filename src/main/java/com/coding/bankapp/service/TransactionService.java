package com.coding.bankapp.service;

import com.coding.bankapp.model.Transaction;
import com.coding.bankapp.model.TransferRequest;

import java.util.List;

public interface TransactionService {
     int transferDetails(TransferRequest transferRequest, Long customerNumber);

     List<Transaction> findTransactionsByAccountNumber(Long accountNumber);
}
