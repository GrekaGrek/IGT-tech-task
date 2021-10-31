package com.igt.technical.task.controller;

import com.igt.technical.task.controller.interfaces.CustomerDebtCaseResourceAPIDefinition;
import com.igt.technical.task.model.CustomerDebtCaseDTO;
import com.igt.technical.task.service.CustomerDebtCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CustomerDebtCaseResource implements CustomerDebtCaseResourceAPIDefinition {

    private final CustomerDebtCaseService service;

    @Autowired
    public CustomerDebtCaseResource(CustomerDebtCaseService service) {
        this.service = service;
    }

    @Override
    @PostMapping(value = "/debt-cases")
    public ResponseEntity createCustomerDebtCase(@Valid @RequestBody CustomerDebtCaseDTO customerDebtCase) {
        service.createCustomerDebtCase(customerDebtCase);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @GetMapping(value = "/debt-cases/{id}")
    public ResponseEntity<CustomerDebtCaseDTO> getCustomerDebtCase(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCustomerDebtCase(id));
    }

    @Override
    @PutMapping(value = "/debt-cases/{id}")
    public ResponseEntity<CustomerDebtCaseDTO> updateCustomerDebtCase(@Valid @RequestBody CustomerDebtCaseDTO customerDebtCase,
                                                                      @PathVariable Long id) {
        return ResponseEntity.ok(service.updateCustomerDebtCase(customerDebtCase, id));
    }

    @Override
    @DeleteMapping(value = "/debt-cases/{id}")
    public ResponseEntity<HttpStatus> deleteCustomerDebtCase(@PathVariable Long id) {
        service.deleteCustomerDebtCase(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
