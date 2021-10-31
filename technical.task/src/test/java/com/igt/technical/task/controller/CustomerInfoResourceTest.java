package com.igt.technical.task.controller;

import com.igt.technical.task.model.CustomerInfoDTO;
import com.igt.technical.task.service.CustomerInfoService;
import com.mysql.cj.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.igt.technical.task.controller.TestUtils.getContentFromFile;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(controllers = CustomerDebtCaseResource.class)
@ContextConfiguration(classes = CustomerDebtCaseResource.class)
@ActiveProfiles("test")
class CustomerInfoResourceTest extends TestUtils {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerInfoService customerInfoServiceMock;

    @Test
    void createCustomerInfo() throws Exception {
        String request = getContentFromFile("/input/newcustomerdata.json");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/customers")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").exists());

        Mockito.verify(customerInfoServiceMock).createCustomerInfo(any(CustomerInfoDTO.class));
    }

    @Test
    void createCustomerInfoBadRequest() throws Exception {
        String request = getContentFromFile("/input/newcustomerdata.json");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/customer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        Mockito.verifyNoInteractions(customerInfoServiceMock);
    }

    @Test
    void getCustomerInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/customers/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value(1));

        Mockito.verify(customerInfoServiceMock).getCustomerInfo(1L);
    }

    @Test
    void getCustomerInfoNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/customers/{id}", 4)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        Mockito.verifyNoInteractions(customerInfoServiceMock);
    }

    @Test
    void updateCustomerInfo() throws Exception {
        String request = getContentFromFile("/input/updatecustomerdata.json");

        mockMvc.perform(MockMvcRequestBuilders
                .put("/customers/{id}")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").exists());

        Mockito.verify(customerInfoServiceMock).updateCustomerInfo(any(CustomerInfoDTO.class), 2L);
    }

    @Test
    void updateCustomerInfoBadRequest() throws Exception {
        String request = getContentFromFile("/input/updatecustomerdata.json");

        mockMvc.perform(MockMvcRequestBuilders
                .put("/customer/{id}")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        Mockito.verifyNoInteractions(customerInfoServiceMock);
    }

    @Test
    void deleteCustomerInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/customers/{id}", 1))
                .andExpect(status().isNoContent());

        Mockito.verify(customerInfoServiceMock).deleteCustomerInfo(1L);
    }

    @Test
    void deleteCustomerInfoNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/customers/{id}", 5))
                .andExpect(status().isNotFound());

        Mockito.verifyNoInteractions(customerInfoServiceMock);
    }
}