package com.igt.technical.task.service;

import com.igt.technical.task.domain.CustomerInfoEntity;
import com.igt.technical.task.model.CustomerDebtCaseDTO;
import com.igt.technical.task.model.CustomerInfoDTO;
import com.igt.technical.task.repository.CustomerInfoRepository;
import com.igt.technical.task.service.mappers.CustomerInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

@Service
public class CustomerInfoService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerInfoService.class);

    private final CustomerInfoMapper customerInfoMapper;
    private final CustomerInfoRepository customerRepository;

    @Autowired
    public CustomerInfoService(CustomerInfoMapper customerInfoMapper,
                               CustomerInfoRepository customerRepository) {
        this.customerInfoMapper = customerInfoMapper;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void createCustomerInfo(CustomerInfoDTO customerInfo) {
        Long id = customerInfo.getId();
        logger.debug("Creating new Customer debt case with id:{}", id);

        if (customerRepository.findById(id).isPresent()) {
            throw new EntityExistsException("Customer information already exists");
        }
        customerRepository.save(customerInfoMapper.mapFromDTO(customerInfo));
    }

    public CustomerInfoDTO getCustomerInfo(Long id) {
        CustomerInfoEntity entity = customerRepository.findById(id).orElseThrow(customerInfoNotFound());
        return customerInfoMapper.mapToDTO(entity);
    }

    @Transactional
    public CustomerInfoDTO updateCustomerInfo(CustomerInfoDTO customerInfo, Long id) {
        CustomerInfoEntity entity = customerRepository.findById(id).orElseThrow(customerInfoNotFound());

        customerRepository.save(customerInfoMapper.mapFromDTO(customerInfo));
        logger.debug("Updated Customer info with id: {}.", entity.getId());

        return customerInfoMapper.mapToDTO(entity);
    }

    @Transactional
    public void deleteCustomerInfo(Long id) {
        customerRepository.deleteById(id);
    }

    private Supplier<EntityNotFoundException> customerInfoNotFound() {
        return () -> new EntityNotFoundException("Customer information is not found");
    }
}
