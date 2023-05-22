package com.facens.apimarketplace.domain.service.impl;

import com.facens.apimarketplace.application.dto.stock.StockDTO;
import com.facens.apimarketplace.application.dto.stock.StockUpdateDTO;
import com.facens.apimarketplace.application.exception.BadRequestException;
import com.facens.apimarketplace.domain.factories.StockFatory;
import com.facens.apimarketplace.domain.entities.Stock;
import com.facens.apimarketplace.domain.repository.StockRepository;
import com.facens.apimarketplace.domain.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository repository;

    private String notFoundMessage = "Não existe o estoque na base de dados.";

    @Override
    public List<StockDTO> getStocks() {
        List<Stock> stocks = repository.findAll();
        return stocks.stream().map(StockFatory::createFromModel).toList();
    }

    @Override
    public StockDTO getStocksById(UUID id) {
        Stock stock = repository.findById(id).orElseThrow(() -> new BadRequestException(notFoundMessage, HttpStatus.BAD_REQUEST));
        return StockFatory.createFromModel(stock);
    }

    @Override
    public StockDTO updateStockById(UUID id, StockUpdateDTO stockUpdateDTO) {
        StockDTO existingStock = getStocksById(id);
        Stock stock = StockFatory.createFromModel(stockUpdateDTO, existingStock);
        Stock stockSave = repository.save(stock);
        return StockFatory.createFromModel(stockSave);
    }

}
