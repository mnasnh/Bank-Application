package com.coding.bankapp.controller;

import com.coding.bankapp.dto.Customer;
import com.coding.bankapp.dto.ErrorResponse;
import com.coding.bankapp.exception.BankApiException;
import com.coding.bankapp.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    @Operation(summary = "Get all active customer details")
    @ApiResponse(responseCode = "200", description = "Get all customers",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class)) })
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)) })
    @ApiResponse(responseCode = "500", description = "Technical error",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)) })

    public List<Customer> getAllCustomers() {

        return customerService.findAll();
    }

    @PutMapping
    @Operation(summary = "Add a customer")
    @ApiResponse(responseCode = "200", description = "Added customer",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Customer.class)) })
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)) })
    @ApiResponse(responseCode = "500", description = "Technical error",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)) })
    public Customer addCustomer(@RequestBody Customer customer) {

        return customerService.addCustomer(customer);
    }
    @GetMapping(path = "/{customerNumber}")
    @Operation(summary = "Get customer detail by customer number")
    @ApiResponse(responseCode = "200", description = "Request successful",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Customer.class)) })
    @ApiResponse(responseCode = "500", description = "Technical error",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)) })

    public Customer getCustomer(@PathVariable Long customerNumber) {

        return customerService.findBy(customerNumber);
    }

    @DeleteMapping(path = "/{customerNumber}")
    @Operation(summary = "Deactivate an active customer and close linked accounts")
    @ApiResponse(responseCode = "200", description = "Customer deactivated",
            content = { @Content(mediaType = "text/plain",
                    schema = @Schema(implementation = String.class)) })
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)) })
    @ApiResponse(responseCode = "500", description = "Technical error",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)) })

    public String deleteCustomer(@PathVariable Long customerNumber) throws BankApiException {

         return customerService.deleteCustomer(customerNumber);
    }
}
