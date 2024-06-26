package com.coding.bankapp.exception;

public class EventMessage {




    private EventMessage()
    {

    }

    public static final String CUSTOMER_DOES_NOT_EXIST = "Customer Id: %d not found.";
    public static final String ACCOUNT_DOES_NOT_EXIST = "Account number %d not found.";
    public static final String INSUFFICIENT_FUNDS = "Insufficient funds in account: %d";
    public static final String INVALID_BRANCH_ID = "Invalid branch Id: %d";
    public static final String CUSTOMER_INACTIVE = "Customer number: %d is inactive";
    public static final String ACCOUNT_CLOSED = "Account number: %d is closed";
    public static final String TRANSACTION_FAILED_NOT_UNIQUE = "Transaction %s already processed (id non-unique)";
    public static final String INCORRECT_ACCOUNT_FOR_CUSTOMER = "Account number %d does not belong to customer %d";
    public static final String TRANSACTION_FAILED_AMOUNT_INVALID = "Transaction amount must be larger than 0; Received %s";

}
