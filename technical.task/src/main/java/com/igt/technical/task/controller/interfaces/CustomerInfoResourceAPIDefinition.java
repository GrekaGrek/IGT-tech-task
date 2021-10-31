package com.igt.technical.task.controller.interfaces;

import com.igt.technical.task.exception.TechnicalException;
import com.igt.technical.task.model.CustomerInfoDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface CustomerInfoResourceAPIDefinition {

    @ApiOperation(value = "Create new Customer", nickname = "createCustomerInfo",
            notes = "New record with information about Customer", response = CustomerInfoDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Successful operation", response = CustomerInfoDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = TechnicalException.class)})
    ResponseEntity createCustomerInfo(@Valid @RequestBody CustomerInfoDTO customerInfo);

    @ApiOperation(value = "Fetch information about Customer by provided id", nickname = "getCustomerInfo",
            notes = "Return information about existed Customer", response = CustomerInfoDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Successful operation", response = CustomerInfoDTO.class),
            @ApiResponse(code = 404, message = "Customer information not found by provided id", response = TechnicalException.class)})
    ResponseEntity<CustomerInfoDTO> getCustomerInfo(@PathVariable Long id);

    @ApiOperation(value = "Update information about Customer", nickname = "updateCustomerInfo",
            notes = "Update information about existed Customer", response = CustomerInfoDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Successful operation", response = CustomerInfoDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = TechnicalException.class)})
    ResponseEntity<CustomerInfoDTO> updateCustomerInfo(@Valid @RequestBody CustomerInfoDTO customerInfo, @PathVariable Long id);

    @ApiOperation(value = "Delete existed Customer information by id", nickname = "deleteCustomerInfo",
            notes = "Deleting information about Customer", response = CustomerInfoDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code = 204, message = "Successful operation", response = CustomerInfoDTO.class),
            @ApiResponse(code = 404, message = "Customer information not found by provided id", response = TechnicalException.class)})
    ResponseEntity<HttpStatus> deleteCustomerInfo(@PathVariable Long id);
}
