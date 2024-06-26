package com.coding.bankapp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class BankApiException extends Exception {

    private final HttpStatusCode httpStatusCode;
    private final String errorMessage;

    public BankApiException(HttpStatusCode httpStatusCode, String errorMessage) {
        super(errorMessage);
        this.httpStatusCode = httpStatusCode;
        this.errorMessage = errorMessage;

    }
}
