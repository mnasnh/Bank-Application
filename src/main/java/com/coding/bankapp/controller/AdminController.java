package com.coding.bankapp.controller;

import com.coding.bankapp.dto.Branch;
import com.coding.bankapp.dto.Customer;
import com.coding.bankapp.dto.ErrorResponse;
import com.coding.bankapp.repository.BranchRepository;
import com.coding.bankapp.repository.entity.BranchEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private BranchRepository branchRepository;

    @PostMapping("/branches")
    @Operation(summary = "Add a branch")
    @ApiResponse(responseCode = "200", description = "Added branch",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Customer.class))})
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))})
    @ApiResponse(responseCode = "500", description = "Technical error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))})
    public Branch addBranch(@RequestBody Branch branch) {

        BranchEntity branchEntity = branchRepository.save(BranchEntity.from(branch));
        return Branch.from(branchEntity);
    }

    @GetMapping("/branches")

    @Operation(summary = "Get all branches")
    @ApiResponse(responseCode = "200", description = "Request successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))})
    @ApiResponse(responseCode = "500", description = "Technical error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))})
    public List<Branch> getAllBranches() {
        List<Branch> branchList = new ArrayList<>();
        List<BranchEntity> branchEntities = (List<BranchEntity>) branchRepository.findAll();
        if (branchEntities.isEmpty()) {
            return branchList;
        }
        branchEntities.forEach(branchEntity ->
                branchList.add(Branch.from(branchEntity)));

        return branchList;

    }

}
