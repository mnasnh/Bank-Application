package com.coding.bankapp.service.impl;

import com.coding.bankapp.dto.DepositWithdrawRequest;
import com.coding.bankapp.dto.Transaction;
import com.coding.bankapp.dto.TransferRequest;
import com.coding.bankapp.enums.AccountStatus;
import com.coding.bankapp.enums.CustomerStatus;
import com.coding.bankapp.enums.TransactionType;
import com.coding.bankapp.exception.BankApiException;
import com.coding.bankapp.exception.EventMessage;
import com.coding.bankapp.repository.AccountRepository;
import com.coding.bankapp.repository.CustomerAccountRepository;
import com.coding.bankapp.repository.CustomerRepository;
import com.coding.bankapp.repository.TransactionRepository;
import com.coding.bankapp.repository.entity.AccountEntity;
import com.coding.bankapp.repository.entity.CustomerAccountEntity;
import com.coding.bankapp.repository.entity.CustomerEntity;
import com.coding.bankapp.repository.entity.TransactionEntity;
import com.coding.bankapp.service.TransactionService;
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
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public String transfer(TransferRequest transferRequest) throws BankApiException {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findByCustomerNumberAndStatus(transferRequest.getCustomerNumber(), CustomerStatus.ENABLED.toString());
        if(!customerEntityOptional.isPresent())
        {
            throw new BankApiException(HttpStatusCode.valueOf(400), EventMessage.CUSTOMER_DOES_NOT_EXIST.formatted(transferRequest.getCustomerNumber()));
        }

        Optional<AccountEntity> fromAccountEntityOptional = accountRepository.findByAccountNumberAndAccountStatus(transferRequest.getFromAccountNumber(), AccountStatus.ACTIVE.toString());
        if(!fromAccountEntityOptional.isPresent())
        {
            throw new BankApiException(HttpStatusCode.valueOf(400), EventMessage.ACCOUNT_DOES_NOT_EXIST.formatted(transferRequest.getFromAccountNumber()));

        }
        Optional<CustomerAccountEntity> customerAccountEntityOptional = customerAccountRepository.findByCustomerNumberAndAccountNumber(transferRequest.getCustomerNumber(), transferRequest.getFromAccountNumber());
        if(!customerAccountEntityOptional.isPresent())
        {
            throw new BankApiException(HttpStatusCode.valueOf(400), EventMessage.INCORRECT_ACCOUNT_FOR_CUSTOMER.formatted(transferRequest.getFromAccountNumber(), transferRequest.getCustomerNumber()));
        }
        Optional<AccountEntity> toAccountEntityOptional = accountRepository.findByAccountNumberAndAccountStatus(transferRequest.getToAccountNumber(), AccountStatus.ACTIVE.toString());
        if(!toAccountEntityOptional.isPresent())
        {
            throw new BankApiException(HttpStatusCode.valueOf(400), EventMessage.ACCOUNT_DOES_NOT_EXIST.formatted(transferRequest.getToAccountNumber()));

        }
       AccountEntity fromAccountEntity = fromAccountEntityOptional.get();
        AccountEntity toAccountEntity = toAccountEntityOptional.get();
        if(fromAccountEntity.getAccountBalance().compareTo(transferRequest.getTransferAmount())<0)
        {
            throw new BankApiException(HttpStatusCode.valueOf(400), EventMessage.INSUFFICIENT_FUNDS.formatted(transferRequest.getFromAccountNumber()));
        }
        fromAccountEntity.setAccountBalance(fromAccountEntity.getAccountBalance().subtract(transferRequest.getTransferAmount()));
        fromAccountEntity.setUpdateDateTime(new Date());
        toAccountEntity.setAccountBalance(toAccountEntity.getAccountBalance().add(transferRequest.getTransferAmount()));
        toAccountEntity.setUpdateDateTime(new Date());
        accountRepository.save(fromAccountEntity);
        accountRepository.save(toAccountEntity);

        Date txDate = new Date();
        TransactionEntity transactionEntity1 = TransactionEntity.builder().transactionDate(txDate).transactionAmount(transferRequest.getTransferAmount())
                .transactionType(TransactionType.WITHDRAW.toString()).accountNumber(transferRequest.getFromAccountNumber()).build();
        transactionRepository.save(transactionEntity1);

        TransactionEntity transactionEntity2 = TransactionEntity.builder().transactionDate(txDate).transactionAmount(transferRequest.getTransferAmount())
                .transactionType(TransactionType.DEPOSIT.toString()).accountNumber(transferRequest.getToAccountNumber()).build();
        transactionRepository.save(transactionEntity2);

        return "Success: Amount transferred successfully from account number:" + transferRequest.getFromAccountNumber() + " to account number:" + transferRequest.getToAccountNumber();
    }

    @Override
    public String deposit(DepositWithdrawRequest  depositWithdrawRequest) throws BankApiException {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findByCustomerNumberAndStatus(depositWithdrawRequest.getCustomerNumber(), CustomerStatus.ENABLED.toString());
        if(!customerEntityOptional.isPresent())
        {
            throw new BankApiException(HttpStatusCode.valueOf(400), EventMessage.CUSTOMER_DOES_NOT_EXIST.formatted(depositWithdrawRequest.getCustomerNumber()));
        }

        Optional<AccountEntity> accountEntityOptional = accountRepository.findByAccountNumberAndAccountStatus(depositWithdrawRequest.getAccountNumber(), AccountStatus.ACTIVE.toString());
        if(!accountEntityOptional.isPresent())
        {
            throw new BankApiException(HttpStatusCode.valueOf(400), EventMessage.ACCOUNT_DOES_NOT_EXIST.formatted(depositWithdrawRequest.getAccountNumber()));

        }
        Date txDate = new Date();
        AccountEntity accountEntity = accountEntityOptional.get();
        accountEntity.setAccountBalance(accountEntity.getAccountBalance().add(depositWithdrawRequest.getAmount()));
        accountEntity.setUpdateDateTime(txDate);
        accountRepository.save(accountEntity);

        TransactionEntity transactionEntity1 = TransactionEntity.builder().transactionDate(txDate).transactionAmount(depositWithdrawRequest.getAmount())
                .transactionType(TransactionType.DEPOSIT.toString()).accountNumber(depositWithdrawRequest.getAccountNumber()).build();
        transactionRepository.save(transactionEntity1);
        return String.format("Success: Amount: %f deposited successfully for account number: %d. New account balance is : %f", depositWithdrawRequest.getAmount(), depositWithdrawRequest.getAccountNumber(), accountEntity.getAccountBalance());
    }

    @Override
    public String withdraw(DepositWithdrawRequest depositWithdrawRequest) throws BankApiException {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findByCustomerNumberAndStatus(depositWithdrawRequest.getCustomerNumber(), CustomerStatus.ENABLED.toString());
        if(!customerEntityOptional.isPresent())
        {
            throw new BankApiException(HttpStatusCode.valueOf(400), EventMessage.CUSTOMER_DOES_NOT_EXIST.formatted(depositWithdrawRequest.getCustomerNumber()));
        }
        Optional<AccountEntity> accountEntityOptional = accountRepository.findByAccountNumberAndAccountStatus(depositWithdrawRequest.getAccountNumber(), AccountStatus.ACTIVE.toString());
        if(!accountEntityOptional.isPresent())
        {
            throw new BankApiException(HttpStatusCode.valueOf(400), EventMessage.ACCOUNT_DOES_NOT_EXIST.formatted(depositWithdrawRequest.getAccountNumber()));

        }
        AccountEntity accountEntity = accountEntityOptional.get();
        if(accountEntity.getAccountBalance().compareTo(depositWithdrawRequest.getAmount())<0)
        {
            throw new BankApiException(HttpStatusCode.valueOf(400), EventMessage.INSUFFICIENT_FUNDS.formatted(depositWithdrawRequest.getAccountNumber()));
        }
        Date txDate = new Date();
        accountEntity.setAccountBalance(accountEntity.getAccountBalance().subtract(depositWithdrawRequest.getAmount()));
        accountEntity.setUpdateDateTime(txDate);
        accountRepository.save(accountEntity);

        TransactionEntity transactionEntity1 = TransactionEntity.builder().transactionDate(txDate).transactionAmount(depositWithdrawRequest.getAmount())
                .transactionType(TransactionType.WITHDRAW.toString()).accountNumber(depositWithdrawRequest.getAccountNumber()).build();
        transactionRepository.save(transactionEntity1);
        return String.format("Success: Amount: %f withdrawn successfully for account number: %d. New account balance is : %f", depositWithdrawRequest.getAmount(), depositWithdrawRequest.getAccountNumber(), accountEntity.getAccountBalance());
    }

    @Override
    public List<Transaction> findTransactionsByAccountNumber(Long accountNumber) {
        List<Transaction> transactionDetails = new ArrayList<>();
        Optional<AccountEntity> accountEntityOpt = accountRepository.findByAccountNumberAndAccountStatus(accountNumber, AccountStatus.ACTIVE.toString());
        if(accountEntityOpt.isPresent()) {
            Optional<List<TransactionEntity>> transactionEntitiesOpt = transactionRepository.findByAccountNumberOrderByTransactionDateDesc(accountNumber);
            if(transactionEntitiesOpt.isPresent()) {
                transactionEntitiesOpt.get().forEach(transactionEntity -> {
                    transactionDetails.add(Transaction.from(transactionEntity));
                });
            }
        }

        return transactionDetails;
    }

}
