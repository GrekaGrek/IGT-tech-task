package com.igt.technical.task.service.mappers;

import com.igt.technical.task.domain.CustomerDebtCaseEntity;
import com.igt.technical.task.model.CustomerDebtCaseDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomerDebtCaseMapperTest {

    private CustomerDebtCaseMapper mapper = new CustomerDebtCaseMapper();

    @Test
    void mapToDTO() {
        CustomerDebtCaseEntity entity = getEntity();

        CustomerDebtCaseDTO item = mapper.mapToDTO(entity);

        assertThat(item).usingRecursiveComparison().isEqualTo(entity);
    }

    @Test
    void mapFromDTO() {
        CustomerDebtCaseDTO item = getItem();

        CustomerDebtCaseEntity entity = mapper.mapFromDTO(item);

        assertThat(entity).usingRecursiveComparison().isEqualTo(item);
    }

    private CustomerDebtCaseEntity getEntity() {
        return new CustomerDebtCaseEntity()
                .setId(1L)
                .setAmount(280.50)
                .setCurrency("NOK")
                .setDueDate(LocalDate.of(2020, 11, 1));
    }

    private CustomerDebtCaseDTO getItem() {
        return CustomerDebtCaseDTO.builder()
                .id(1L)
                .amount(280.50)
                .currency("NOK")
                .dueDate(LocalDate.of(2020, 11, 1))
                .build();
    }
}