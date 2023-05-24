package com.facens.apimarketplace.domain.service;

import com.facens.apimarketplace.application.dto.stock.StockDTO;
import com.facens.apimarketplace.infrastructure.repository.StockRepository;
import com.facens.apimarketplace.domain.service.impl.StockServiceImpl;
import com.facens.apimarketplace.mocks.MocksStock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.facens.apimarketplace.mocks.MocksStock.STOCK_AMOUNT;
import static com.facens.apimarketplace.mocks.MocksStock.STOCK_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class StockServiceTest {

    @InjectMocks
    @Spy
    private StockServiceImpl service;

    @Mock
    private StockRepository repository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(service, "repository", repository);
    }

    @DisplayName("Deve buscar estoques")
    @Test
    void whenGetProductsThenReturnsEmptyList(){
        when(repository.findAll()).thenReturn(new ArrayList<>());
        List<StockDTO> stocks = service.getStocks();
        assertEquals(new ArrayList<>(), stocks);
    }

    @DisplayName("Deve buscar estoque por id")
    @Test
    void whenGetProductByIdThenReturnProduct(){
        when(repository.findById(STOCK_ID))
                .thenReturn(Optional.ofNullable(MocksStock.createStock(STOCK_ID, STOCK_AMOUNT)));

        StockDTO stock = service.getStocksById(STOCK_ID);
        assertTrue(Objects.nonNull(stock));
    }

    @DisplayName("Deve atualizar estoque por id")
    @Test
    void whenGetProductByIdThenReturnProduct1(){
        when(repository.findById(STOCK_ID))
                .thenReturn(Optional.ofNullable(MocksStock.createStock(STOCK_ID, STOCK_AMOUNT)));
        when(repository.save(any()))
                .thenReturn(MocksStock.createStock(STOCK_ID, STOCK_AMOUNT));

        StockDTO stock = service.updateStockById(STOCK_ID, MocksStock.createStockUpdateDTO(STOCK_AMOUNT));
        assertTrue(Objects.nonNull(stock));
    }



}
