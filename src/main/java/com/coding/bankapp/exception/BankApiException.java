package com.coding.bankapp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;
@Getter
public class BankApiException extends Exception{

    private HttpStatusCode httpStatusCode;
    private String errorMessage;

    public BankApiException(String errorMessage)
    {
        super(errorMessage);
        this.errorMessage = errorMessage;


    }

    public BankApiException(HttpStatusCode httpStatusCode, String errorMessage)
    {
        super(errorMessage);
        this.httpStatusCode = httpStatusCode;
        this.errorMessage = errorMessage;

    }
}
