package com.igt.technical.task.service.mappers;

import com.igt.technical.task.domain.CustomerDebtCaseEntity;
import com.igt.technical.task.model.CustomerDebtCaseDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerDebtCaseMapper {

    public CustomerDebtCaseDTO mapToDTO(CustomerDebtCaseEntity customerDebtCase) {
        if (customerDebtCase == null) {
            return null;
        }
        return CustomerDebtCaseDTO.builder()
                .id(customerDebtCase.getId())
                .amount(customerDebtCase.getAmount())
                .currency(customerDebtCase.getCurrency())
                .dueDate(customerDebtCase.getDueDate())
                .build();
    }

    public CustomerDebtCaseEntity mapFromDTO(CustomerDebtCaseDTO customerDebtCaseDTO) {
        if (customerDebtCaseDTO == null) {
            return null;
        }
        return new CustomerDebtCaseEntity()
                .setAmount(customerDebtCaseDTO.getAmount())
                .setCurrency(customerDebtCaseDTO.getCurrency())
                .setDueDate(customerDebtCaseDTO.getDueDate());
    }
}
