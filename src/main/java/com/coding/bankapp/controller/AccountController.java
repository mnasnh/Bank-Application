package com.coding.bankapp.controller;

import com.coding.bankapp.dto.Account;
import com.coding.bankapp.dto.ErrorResponse;
import com.coding.bankapp.exception.BankApiException;
import com.coding.bankapp.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(path = "/{accountNumber}")
    @Operation(summary = "Get active account details by account number")
    @ApiResponse(responseCode = "200", description = "Found the account",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Account.class)) })
    @ApiResponse(responseCode = "500", description = "Technical error",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)) })
    public Account getByAccountNumber(@PathVariable Long accountNumber) {

        return accountService.findByAccountNumber(accountNumber);
    }

    @PutMapping(path = "/{customerNumber}")
    @Operation(summary = "Create a new account for existing active customer.")
    @ApiResponse(responseCode = "200", description = "Created new account",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Account.class)) })
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)) })
    @ApiResponse(responseCode = "500", description = "Technical error",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)) })

    public Account addNewAccount(@RequestBody Account account,
                                                @PathVariable Long customerNumber) throws BankApiException {

        return accountService.addNewAccount(account, customerNumber);
    }

    @PostMapping(path = "/close/{accountNumber}")
    @Operation(summary = "Close an active account")
    @ApiResponse(responseCode = "200", description = "Closed",
            content = { @Content(mediaType = "text/plain",
                    schema = @Schema(implementation = String.class)) })
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)) })
    @ApiResponse(responseCode = "500", description = "Technical error",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)) })

    public String closeAccount(@PathVariable Long accountNumber) throws BankApiException {

        return accountService.closeAccount(accountNumber);
    }


}
