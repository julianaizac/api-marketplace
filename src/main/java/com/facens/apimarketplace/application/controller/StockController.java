package com.facens.apimarketplace.application.controller;

import com.facens.apimarketplace.application.dto.stock.StockDTO;
import com.facens.apimarketplace.application.dto.stock.StockUpdateDTO;
import com.facens.apimarketplace.domain.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockService service;

    @GetMapping
    public ResponseEntity<List<StockDTO>> getStocks(){
        List<StockDTO> stocks = service.getStocks();
        return ResponseEntity.ok(stocks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDTO> getStockById(@PathVariable UUID id){
        StockDTO stock = service.getStocksById(id);
        return ResponseEntity.ok(stock);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StockDTO> updateStockById(@PathVariable UUID id, @Validated @RequestBody StockUpdateDTO stockUpdateDTO){
        StockDTO stock = service.updateStockById(id, stockUpdateDTO);
        return ResponseEntity.ok(stock);
    }

}
