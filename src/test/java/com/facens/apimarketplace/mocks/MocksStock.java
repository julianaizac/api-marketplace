package com.facens.apimarketplace.mocks;

import com.facens.apimarketplace.application.dto.stock.StockDTO;
import com.facens.apimarketplace.domain.model.Stock;

import java.time.LocalDateTime;
import java.util.UUID;

public class MocksStock {


    public static final UUID STOCK_ID = UUID.randomUUID();
    public static final Integer STOCK_AMOUNT=  10;

    public static Stock createStock(UUID stockId, Integer stockAmount){
        return Stock.builder()
                .id(stockId)
                .creationDate(LocalDateTime.now())
                .amount(stockAmount)
                .product(null)
                .build();
    }

    public static StockDTO createStockDTO(UUID stockId, Integer stockAmount) {
        return StockDTO.builder()
                .id(stockId)
                .amount(stockAmount)
                .creationDate(LocalDateTime.now())
                .build();
    }
}
