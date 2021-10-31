package com.igt.technical.task.service;

import com.igt.technical.task.domain.CustomerDebtCaseEntity;
import com.igt.technical.task.domain.CustomerInfoEntity;
import com.igt.technical.task.model.CustomerDebtCaseDTO;
import com.igt.technical.task.model.CustomerInfoDTO;
import com.igt.technical.task.repository.CustomerInfoRepository;
import com.igt.technical.task.service.mappers.CustomerInfoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CustomerInfoServiceTest {

    private static final String NAME = "Test";
    private static final String SURNAME = "Tester";
    private static final String EMAIL = "some@inbox.com";
    private static final String PASSWORD = "21!qweyerT";
    private static final String COUNTRY = "China";

    @Mock
    private CustomerInfoMapper mapperMock;
    @Mock
    private CustomerInfoRepository repositoryMock;

    @InjectMocks
    private CustomerInfoService serviceMock;

    @Test
    void createCustomerInfo() {
        CustomerInfoEntity entity = getEntity();
        CustomerInfoDTO item = getItem();

        when(mapperMock.mapFromDTO(item)).thenReturn(entity);
        when(repositoryMock.save(entity)).thenReturn(entity);

        serviceMock.createCustomerInfo(item);

        verify(mapperMock).mapFromDTO(item);
        verify(repositoryMock).save(entity);
    }

    @Test
    void createCustomerInfoWhinExceptionCauseRecordExist() {
        CustomerInfoEntity entity = getExistedEntity();
        CustomerInfoDTO item = getItem();

        when(repositoryMock.findById(getExistedEntity().getId())).thenReturn(Optional.of(entity));

        assertThatThrownBy(() -> serviceMock.createCustomerInfo(item)).isInstanceOf(EntityExistsException.class);

        verify(mapperMock, never()).mapFromDTO(any(CustomerInfoDTO.class));
        verify(repositoryMock, never()).save(any(CustomerInfoEntity.class));
    }

    @Test
    void getCustomerInfo() {
        CustomerInfoEntity entity = getExistedEntity();
        CustomerInfoDTO item = getItem();

        when(repositoryMock.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(mapperMock.mapToDTO(entity)).thenReturn(item);

        serviceMock.getCustomerInfo(item.getId());

        verify(repositoryMock).findById(entity.getId());
        verify(mapperMock).mapToDTO(entity);
    }

    @Test
    void updateCustomerInfo() {
        CustomerInfoEntity entity = getExistedEntity();
        CustomerInfoDTO item = getItem();

        when(repositoryMock.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(mapperMock.mapToDTO(entity)).thenReturn(item);
        when(mapperMock.mapFromDTO(any(CustomerInfoDTO.class))).thenReturn(entity);

        serviceMock.updateCustomerInfo(item, anyLong());

        verify(repositoryMock).findById(entity.getId());
        verify(serviceMock).updateCustomerInfo(item, anyLong());
    }

    @Test
    void updateCustomerInfoWhenItNotExist() {
        CustomerInfoEntity entity = getEntity();
        CustomerInfoDTO item = getItem();

        when(repositoryMock.findById(entity.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> serviceMock.updateCustomerInfo(item, entity.getId()))
                .isInstanceOf(EntityNotFoundException.class);

        verify(repositoryMock).findById(entity.getId());
        verify(mapperMock, never()).mapFromDTO(any(CustomerInfoDTO.class));
        verify(repositoryMock, never()).save(any(CustomerInfoEntity.class));
        verify(mapperMock, never()).mapToDTO(any(CustomerInfoEntity.class));
    }

    @Test
    void deleteCustomerInfo() {
        CustomerInfoEntity entity = getExistedEntity();
        CustomerInfoDTO item = getItem();

        when(repositoryMock.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(mapperMock.mapToDTO(entity)).thenReturn(item);

        serviceMock.deleteCustomerInfo(item.getId());

        verify(repositoryMock).findById(entity.getId());
        verify(serviceMock).deleteCustomerInfo(item.getId());
    }

    private CustomerInfoEntity getEntity() {
        return new CustomerInfoEntity()
                .setUsername(NAME)
                .setSurname(SURNAME)
                .setEmail(EMAIL)
                .setPassword(PASSWORD)
                .setCountry(COUNTRY)
                .setCustomerDebtCases(Collections.singletonList(new CustomerDebtCaseEntity()));
    }

    private CustomerInfoEntity getExistedEntity() {
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