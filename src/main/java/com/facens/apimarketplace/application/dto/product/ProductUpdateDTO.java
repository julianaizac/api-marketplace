package com.facens.apimarketplace.application.dto.product;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductUpdateDTO {

    private String name;
    private String description;
    private BigDecimal price;

}
