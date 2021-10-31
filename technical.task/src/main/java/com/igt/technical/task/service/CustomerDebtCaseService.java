package com.igt.technical.task.service;

import com.igt.technical.task.domain.CustomerDebtCaseEntity;
import com.igt.technical.task.model.CustomerDebtCaseDTO;
import com.igt.technical.task.repository.CustomerDebtCaseRepository;
import com.igt.technical.task.service.mappers.CustomerDebtCaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

@Service
public class CustomerDebtCaseService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerDebtCaseService.class);

    private final CustomerDebtCaseMapper customerDCmapper;
    private final CustomerDebtCaseRepository customerDCRepository;

    @Autowired
    public CustomerDebtCaseService(CustomerDebtCaseMapper customerDCmapper,
                                   CustomerDebtCaseRepository customerDCRepository) {
        this.customerDCmapper = customerDCmapper;
        this.customerDCRepository = customerDCRepository;
    }

    @Transactional
    public void createCustomerDebtCase(CustomerDebtCaseDTO customerDebtCase) {
        Long id = customerDebtCase.getId();
        logger.debug("Creating new Customer debt case with id:{}", id);
        if(customerDCRepository.findById(id).isPresent()) {
            throw new EntityExistsException("Customer debt case already exists");
        }
        customerDCRepository.save(customerDCmapper.mapFromDTO(customerDebtCase));
    }

    public CustomerDebtCaseDTO getCustomerDebtCase(Long id) {
        CustomerDebtCaseEntity entity = customerDCRepository.findById(id).orElseThrow(customerDebtCaseNotFound());
        return customerDCmapper.mapToDTO(entity);
    }

    @Transactional
    public CustomerDebtCaseDTO updateCustomerDebtCase(CustomerDebtCaseDTO customerInfo, Long id) {
        CustomerDebtCaseEntity entity = customerDCRepository.findById(id).orElseThrow(customerDebtCaseNotFound());

        customerDCRepository.save(customerDCmapper.mapFromDTO(customerInfo));
        logger.debug("Updated Customer debt case with id: {}.", entity.getId());

        return customerDCmapper.mapToDTO(entity);
    }

    @Transactional
    public void deleteCustomerDebtCase(Long id) {
        customerDCRepository.deleteById(id);
    }

    private Supplier<EntityNotFoundException> customerDebtCaseNotFound() {
        return () -> new EntityNotFoundException("Customer debt case is not found");
    }
}
