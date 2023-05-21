package com.facens.apimarketplace.domain.factories;

import com.facens.apimarketplace.application.dto.stock.StockDTO;
import com.facens.apimarketplace.application.dto.stock.StockUpdateDTO;
import com.facens.apimarketplace.domain.model.Stock;

import java.util.Objects;

import static java.util.Objects.nonNull;

public class StockFatory {
    public static StockDTO createFromModel(Stock stock) {

        return StockDTO.builder()
                .id(stock.getId())
                .amount(stock.getAmount())
                .creationDate(stock.getCreationDate())
                .build();

    }

    public static Stock createFromModel(StockUpdateDTO stockUpdateDTO, StockDTO existingStock) {
        return Stock.builder()
                .id(existingStock.getId())
                .amount(nonNull(stockUpdateDTO.getAmount()) ? stockUpdateDTO.getAmount() : existingStock.getAmount())
                .creationDate(existingStock.getCreationDate())
                .build();
    }

    public static Stock createFromStockDTO(StockDTO stock) {
        return Stock.builder()
                .id(stock.getId())
                .amount(stock.getAmount())
                .creationDate(stock.getCreationDate())
                .build();
    }
}
