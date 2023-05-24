package com.facens.apimarketplace.domain.factories;

import com.facens.apimarketplace.application.dto.category.CategoryDTO;
import com.facens.apimarketplace.application.dto.product.ProductDTO;
import com.facens.apimarketplace.application.dto.product.ProductInsertDTO;
import com.facens.apimarketplace.application.dto.product.ProductUpdateDTO;
import com.facens.apimarketplace.domain.entities.Product;

import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

public class ProductFactory {

    public static Product createFromInsertDTO(ProductInsertDTO productInsertDTO, CategoryDTO categoryDTO){
        return Product.builder()
                .name(productInsertDTO.getName())
                .description(productInsertDTO.getDescription())
                .price(productInsertDTO.getPrice())
                .creationDate(LocalDateTime.now())
                .category(CategoryFactory.createFromCategoryDTO(categoryDTO))
                .stock(null)
                .build();
    }

    public static Product createFromUpdateDTO(ProductUpdateDTO productUpdateDTO, ProductDTO productDTO){
        return Product.builder()
                .id(productDTO.getId())
                .name(nonNull(productUpdateDTO.getName()) ? productUpdateDTO.getName() : productDTO.getName())
                .description(nonNull(productUpdateDTO.getDescription()) ? productUpdateDTO.getDescription() : productDTO.getDescription())
                .price(nonNull(productUpdateDTO.getPrice()) ? productUpdateDTO.getPrice() : productDTO.getPrice())
                .category(CategoryFactory.createFromCategoryDTO(productDTO.getCategory()))
                .stock(StockFatory.createFromStockDTO(productDTO.getStock()))
                .build();
    }

    public static ProductDTO createFromModel(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .creationDate(product.getCreationDate())
                .category(CategoryFactory.createFromModel(product.getCategory()))
                .stock(StockFatory.createFromModel(product.getStock()))
                .build();
    }

}
