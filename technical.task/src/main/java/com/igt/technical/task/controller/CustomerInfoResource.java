package com.igt.technical.task.controller;

import com.igt.technical.task.controller.interfaces.CustomerInfoResourceAPIDefinition;
import com.igt.technical.task.model.CustomerInfoDTO;
import com.igt.technical.task.service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CustomerInfoResource implements CustomerInfoResourceAPIDefinition {

    private final CustomerInfoService service;

    @Autowired
    public CustomerInfoResource(CustomerInfoService service) {
        this.service = service;
    }

    @Override
    @PostMapping(value = "/customers")
    public ResponseEntity createCustomerInfo(@Valid @RequestBody CustomerInfoDTO customerInfo) {
        service.createCustomerInfo(customerInfo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<CustomerInfoDTO> getCustomerInfo(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCustomerInfo(id));
    }

    @Override
    @PutMapping(value = "/customers/{id}")
    public ResponseEntity<CustomerInfoDTO> updateCustomerInfo(@Valid @RequestBody CustomerInfoDTO customerInfo,
                                                              @PathVariable Long id) {
        return ResponseEntity.ok(service.updateCustomerInfo(customerInfo, id));
    }

    @Override
    @DeleteMapping(value = "/customers/{id}")
    public ResponseEntity<HttpStatus> deleteCustomerInfo(@PathVariable Long id) {
        service.deleteCustomerInfo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
