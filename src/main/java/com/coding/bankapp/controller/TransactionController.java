package com.coding.bankapp.controller;

import com.coding.bankapp.dto.*;
import com.coding.bankapp.exception.BankApiException;
import com.coding.bankapp.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping(path = "/transfer")
    @Operation(summary = "Transfer amount. Customer number and Debit and credit accounts must be active in the system.")
    @ApiResponse(responseCode = "200", description = "Transfer successful",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class)) })
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)) })
    @ApiResponse(responseCode = "500", description = "Technical error",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)) })

    public String transfer(@RequestBody TransferRequest transferRequest) throws BankApiException {

        return transactionService.transfer(transferRequest);

    }

    @PostMapping(path = "/withdraw")
    @Operation(summary = "Debit amount from an active account for an active customer")
    @ApiResponse(responseCode = "200", description = "Debit successful",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class)) })
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)) })
    @ApiResponse(responseCode = "500", description = "Technical error",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)) })

    public String withdrawAmount(@RequestBody DepositWithdrawRequest depositWithdrawRequest) throws BankApiException {

        return transactionService.withdraw(depositWithdrawRequest);

    }

    @PostMapping(path = "/deposit")
    @Operation(summary = "Credit amount from an active account for an active customer")
    @ApiResponse(responseCode = "200", description = "Credit successful",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class)) })
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)) })
    @ApiResponse(responseCode = "500", description = "Technical error",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)) })

    public String depositAmount(@RequestBody DepositWithdrawRequest depositWithdrawRequest) throws BankApiException {

        return transactionService.deposit(depositWithdrawRequest);

    }
    @GetMapping(path = "/{accountNumber}")
    @Operation(summary = "View transactions for all active accounts ordered by transaction date")
    @ApiResponse(responseCode = "200", description = "Request successful",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class)) })
    @ApiResponse(responseCode = "500", description = "Technical error",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)) })
    public List<Transaction> getTransactions(@PathVariable Long accountNumber)
    {
        return transactionService.findTransactionsByAccountNumber(accountNumber);

    }
}
