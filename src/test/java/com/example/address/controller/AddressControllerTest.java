package com.example.address.controller;

import com.example.address.AddressApplication;
import com.example.address.controller.AddressController.AddressRequest;
import com.example.address.model.Address;
import com.example.address.service.AddressService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressController.class)
@Import({AddressApplication.class})
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AddressService addressService;

    @Test
    void shouldCreateAddress() throws Exception {
        Address saved = Address.builder()
                .id("mock-id")
                .street("123 Main St")
                .city("Springfield")
                .state("IL")
                .zipCode("62701")
                .country("USA")
                .build();

        when(addressService.save(any(Address.class))).thenReturn(saved);

        String requestBody = """
                {
                    "street": "123 Main St",
                    "city": "Springfield",
                    "state": "IL",
                    "zipCode": "62701",
                    "country": "USA"
                }
                """;

        mockMvc.perform(post("/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.street").value("123 Main St"))
                .andExpect(jsonPath("$.city").value("Springfield"))
                .andExpect(jsonPath("$.zipCode").value("62701"))
                .andExpect(jsonPath("$.country").value("USA"));
    }

    @Test
    void shouldGetAddressById() throws Exception {
        Address address = Address.builder()
                .id("507f1f77bcf86cd799439011")
                .street("456 Oak Ave")
                .city("Portland")
                .state("OR")
                .zipCode("97201")
                .country("USA")
                .build();

        when(addressService.findById("507f1f77bcf86cd799439011"))
                .thenReturn(Optional.of(address));

        mockMvc.perform(get("/address/507f1f77bcf86cd799439011"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.street").value("456 Oak Ave"))
                .andExpect(jsonPath("$.city").value("Portland"));
    }

    @Test
    void shouldReturn404ForMissingAddress() throws Exception {
        when(addressService.findById("nonexistent-id"))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/address/nonexistent-id"))
                .andExpect(status().isNotFound());
    }
}
