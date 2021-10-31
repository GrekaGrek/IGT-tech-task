package com.igt.technical.task.service.mappers;

import com.igt.technical.task.domain.CustomerDebtCaseEntity;
import com.igt.technical.task.domain.CustomerInfoEntity;
import com.igt.technical.task.model.CustomerDebtCaseDTO;
import com.igt.technical.task.model.CustomerInfoDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CustomerInfoMapperTest {

    private static final String NAME = "Test";
    private static final String SURNAME = "Tester";
    private static final String EMAIL = "some@inbox.com";
    private static final String PASSWORD = "21!qweyerT";
    private static final String COUNTRY = "China";

    @Mock
    private CustomerDebtCaseMapper debtCaseMapperMock;
    @InjectMocks
    private CustomerInfoMapper mapperMock;

    @Test
    void mapToDTO() {
        CustomerInfoEntity entity = getEntity();

        CustomerInfoDTO item = mapperMock.mapToDTO(entity);

        assertThat(item).usingRecursiveComparison().isEqualTo(entity);
    }

    @Test
    void mapFromDTO() {
        CustomerInfoDTO item = getItem();

        when(debtCaseMapperMock.mapFromDTO(any(CustomerDebtCaseDTO.class))).thenReturn(new CustomerDebtCaseEntity());

        CustomerInfoEntity entity = mapperMock.mapFromDTO(item);

        assertThat(entity).usingRecursiveComparison().isEqualTo(item);

        Mockito.verify(debtCaseMapperMock).mapFromDTO(any(CustomerDebtCaseDTO.class));
    }

    private CustomerInfoEntity getEntity() {
        return new CustomerInfoEntity()
                .setId(1L)
                .setUsername(NAME)
                .setSurname(SURNAME)
                .setEmail(EMAIL)
                .setPassword(PASSWORD)
                .setCountry(COUNTRY)
                .setCustomerDebtCases(Collections.singletonList(new CustomerDebtCaseEntity()));
    }

    private CustomerInfoDTO getItem() {
        return CustomerInfoDTO.builder()
                .id(1L)
                .username(NAME)
                .surname(SURNAME)
                .email(EMAIL)
                .password(PASSWORD)
                .country(COUNTRY)
                .customerDebtCases(Collections.singletonList(CustomerDebtCaseDTO.builder().build()))
                .build();
    }
}