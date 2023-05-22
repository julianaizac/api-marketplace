package com.facens.apimarketplace.application.controller;

import com.facens.apimarketplace.domain.service.impl.StockServiceImpl;
import com.facens.apimarketplace.mocks.MocksStock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
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

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.facens.apimarketplace.mocks.MocksStock.STOCK_AMOUNT;
import static com.facens.apimarketplace.mocks.MocksStock.STOCK_ID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig
class StockControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    @Spy
    private StockController controller;

    @Mock
    private StockServiceImpl service;

    private static final String URI = "/stocks";

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @DisplayName("Deve buscar estoques")
    @Test
    void teste1() throws Exception {
        when(service.getStocks()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get(URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("Deve buscar estoque por id")
    @Test
    void teste2() throws Exception {
        when(service.getStocksById(STOCK_ID))
                .thenReturn(MocksStock.createStockDTO(STOCK_ID, STOCK_AMOUNT));

        mockMvc.perform(MockMvcRequestBuilders.get(URI + "/{id}", STOCK_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
