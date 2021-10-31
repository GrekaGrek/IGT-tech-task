package com.igt.technical.task.controller.interfaces;

import com.igt.technical.task.exception.TechnicalException;
import com.igt.technical.task.model.CustomerDebtCaseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface CustomerDebtCaseResourceAPIDefinition {

    @ApiOperation(value = "Create new Customer debt case", nickname = "createCustomerDebtCase",
            notes = "New record with information about Customer debt case", response = CustomerDebtCaseDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Successful operation", response = CustomerDebtCaseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = TechnicalException.class)})
    ResponseEntity createCustomerDebtCase(@Valid @RequestBody CustomerDebtCaseDTO customerDebtCase);

    @ApiOperation(value = "Fetch information about Customer debt case by provided id", nickname = "getCustomerDebtCase",
            notes = "Return information about existed Customer debt case", response = CustomerDebtCaseDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Successful operation", response = CustomerDebtCaseDTO.class),
            @ApiResponse(code = 404, message = "Customer information not found by provided id", response = TechnicalException.class)})
    ResponseEntity<CustomerDebtCaseDTO> getCustomerDebtCase(@PathVariable Long id);

    @ApiOperation(value = "Update information about Customer", nickname = "updateCustomerDebtCase",
            notes = "Update information about existed Customer", response = CustomerDebtCaseDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Successful operation", response = CustomerDebtCaseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = TechnicalException.class)})
    ResponseEntity<CustomerDebtCaseDTO> updateCustomerDebtCase(@Valid @RequestBody CustomerDebtCaseDTO customerDebtCase, @PathVariable Long id);

    @ApiOperation(value = "Delete existed Customer information by id", nickname = "deleteCustomerDebtCase",
            notes = "Deleting information about Customer debt case", response = CustomerDebtCaseDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code = 204, message = "Successful operation", response = CustomerDebtCaseDTO.class),
            @ApiResponse(code = 404, message = "Customer debt case not found by provided id", response = TechnicalException.class)})
    ResponseEntity<HttpStatus> deleteCustomerDebtCase(@PathVariable Long id);
}
