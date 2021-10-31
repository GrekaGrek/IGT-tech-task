package com.igt.technical.task.service.mappers;

import com.igt.technical.task.domain.CustomerDebtCaseEntity;
import com.igt.technical.task.domain.CustomerInfoEntity;
import com.igt.technical.task.model.CustomerDebtCaseDTO;
import com.igt.technical.task.model.CustomerInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerInfoMapper {

    private final CustomerDebtCaseMapper customerDebtCaseMapper;

    @Autowired
    public CustomerInfoMapper(CustomerDebtCaseMapper customerDebtCaseMapper) {
        this.customerDebtCaseMapper = customerDebtCaseMapper;
    }

    public CustomerInfoDTO mapToDTO(CustomerInfoEntity customerInfo) {
        if (customerInfo == null) {
            return null;
        }
        List<CustomerDebtCaseDTO> customerDebtCaseList = customerInfo.getCustomerDebtCases()
                .stream()
                .map(customerDebtCaseMapper::mapToDTO)
                .collect(Collectors.toList());

        return CustomerInfoDTO.builder()
                .id(customerInfo.getId())
                .username(customerInfo.getUsername())
                .surname(customerInfo.getSurname())
                .email(customerInfo.getEmail())
                .password(customerInfo.getPassword())
                .country(customerInfo.getCountry())
                .customerDebtCases(customerDebtCaseList)
                .build();
    }

    public CustomerInfoEntity mapFromDTO(CustomerInfoDTO customerInfoDTO) {
        if (customerInfoDTO == null) {
            return null;
        }
        List<CustomerDebtCaseEntity> customerDebtCaseList = customerInfoDTO.getCustomerDebtCases()
                .stream()
                .map(customerDebtCaseMapper::mapFromDTO)
                .collect(Collectors.toList());

        CustomerInfoEntity customerInfoEntity = new CustomerInfoEntity()
                .setCustomerDebtCases(customerDebtCaseList)
                .setUsername(customerInfoDTO.getUsername())
                .setSurname(customerInfoDTO.getSurname())
                .setEmail(customerInfoDTO.getEmail())
                .setPassword(customerInfoDTO.getPassword())
                .setCountry(customerInfoDTO.getCountry());

        customerInfoEntity.getCustomerDebtCases().forEach(debtCase -> debtCase.setCustomerInfo(customerInfoEntity));

        return customerInfoEntity;
    }
}
