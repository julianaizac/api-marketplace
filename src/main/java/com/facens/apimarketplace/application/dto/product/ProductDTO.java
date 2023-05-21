package com.facens.apimarketplace.application.dto.product;

import com.facens.apimarketplace.application.dto.category.CategoryDTO;
import com.facens.apimarketplace.application.dto.stock.StockDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ProductDTO {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;
    private CategoryDTO category;
    private StockDTO stock;
}
