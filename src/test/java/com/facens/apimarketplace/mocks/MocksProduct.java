package com.facens.apimarketplace.mocks;

import com.facens.apimarketplace.application.dto.product.ProductDTO;
import com.facens.apimarketplace.application.dto.product.ProductInsertDTO;
import com.facens.apimarketplace.application.dto.product.ProductUpdateDTO;
import com.facens.apimarketplace.domain.entities.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class MocksProduct {

    public static final UUID PRODUCT_ID = UUID.randomUUID();
    public static final String PRODUCT_NAME = "Celular";
    public static final String PRODUCT_DESCRIPTION = "Um smartphone avançado";
    public static final BigDecimal PRODUCT_PRICE = BigDecimal.valueOf(3000.00);

    public static Product createProduct(UUID productId, String productName, String productDescription, BigDecimal productPrice,
                                        UUID categoryId, String categoryName, String categoryDescription,
                                        UUID stockId, Integer stockAmount){
        return Product.builder()
                .id(productId)
                .name(productName)
                .description(productDescription)
                .price(productPrice)
                .creationDate(LocalDateTime.now())
                .category(MocksCategory.createCategory(categoryId, categoryName, categoryDescription))
                .stock(MocksStock.createStock(stockId, stockAmount))
                .build();
    }

    public static ProductDTO createProductDTO(UUID productId, String productName, String productDescription, BigDecimal productPrice,
                                              UUID categoryId, String categoryName, String categoryDescription,
                                              UUID stockId, Integer stockAmount){
        return ProductDTO.builder()
                .id(productId)
                .name(productName)
                .description(productDescription)
                .price(productPrice)
                .creationDate(LocalDateTime.now())
                .category(MocksCategory.createCategoryDTO(categoryId, categoryName, categoryDescription))
                .stock(MocksStock.createStockDTO(stockId, stockAmount))
                .build();
    }

    public static ProductInsertDTO createProductInsertDTO(String productName, String productDescription, BigDecimal productPrice,UUID categoryId){
        return ProductInsertDTO.builder()
                .name(productName)
                .description(productDescription)
                .price(productPrice)
                .categoryId(categoryId)
                .build();
    }

    public static ProductUpdateDTO createProductUpdateDTO(String productName, String productDescription, BigDecimal productPrice) {
        return ProductUpdateDTO.builder()
                .name(productName)
                .description(productDescription)
                .price(productPrice)
                .build();
    }
}
