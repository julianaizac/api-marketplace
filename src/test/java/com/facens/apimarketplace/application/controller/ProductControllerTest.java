package com.facens.apimarketplace.application.controller;

import com.facens.apimarketplace.domain.service.impl.ProductServiceImpl;
import com.facens.apimarketplace.mocks.MocksProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static com.facens.apimarketplace.mocks.MocksCategory.*;
import static com.facens.apimarketplace.mocks.MocksCategory.CATEGORY_ID;
import static com.facens.apimarketplace.mocks.MocksProduct.*;
import static com.facens.apimarketplace.mocks.MocksStock.STOCK_AMOUNT;
import static com.facens.apimarketplace.mocks.MocksStock.STOCK_ID;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig
class ProductControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    @Spy
    private ProductController controller;

    @Mock
    private ProductServiceImpl service;

    private static final String URI = "/products";

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @DisplayName("Deve buscar produtos")
    @Test
    void teste1() throws Exception {
        when(service.getProducts()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get(URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("Deve buscar produtos por id")
    @Test
    void teste2() throws Exception {
        when(service.getProductById(PRODUCT_ID))
                .thenReturn(MocksProduct.createProductDTO(PRODUCT_ID, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE,
                        CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION, STOCK_ID, STOCK_AMOUNT));

        mockMvc.perform(MockMvcRequestBuilders.get(URI + "/{id}", PRODUCT_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
