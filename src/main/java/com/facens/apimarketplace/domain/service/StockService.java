package com.facens.apimarketplace.domain.service;

import com.facens.apimarketplace.application.dto.stock.StockDTO;
import com.facens.apimarketplace.application.dto.stock.StockUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface StockService {
    List<StockDTO> getStocks();

    StockDTO getStocksById(UUID id);

    StockDTO updateStockById(UUID id, StockUpdateDTO stockUpdateDTO);

}
