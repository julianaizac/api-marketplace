package com.facens.apimarketplace.mocks;

import com.facens.apimarketplace.application.dto.category.CategoryDTO;
import com.facens.apimarketplace.application.dto.category.CategoryInsertDTO;
import com.facens.apimarketplace.application.dto.category.CategoryUpdateDTO;
import com.facens.apimarketplace.domain.entities.Category;

import java.time.LocalDateTime;
import java.util.UUID;

public class MocksCategory {

    public static final UUID CATEGORY_ID = UUID.randomUUID();
    public static final String CATEGORY_NAME=  "Eletrônicos";
    public static final String CATEGORY_DESCRIPTION = "Categoria de produtos eletrônicos";

    public static Category createCategory(UUID categoryId, String categoryName, String categoryDescription){
        return Category.builder()
                .id(categoryId)
                .name(categoryName)
                .description(categoryDescription)
                .creationDate(LocalDateTime.now())
                .build();
    }

    public static CategoryDTO createCategoryDTO(UUID categoryId, String categoryName, String categoryDescription) {
        return CategoryDTO.builder()
                .id(categoryId)
                .name(categoryName)
                .description(categoryDescription)
                .creationDate(LocalDateTime.now())
                .build();
    }

    public static CategoryInsertDTO createCategoryInsertDTO(String categoryName, String categoryDescription) {
        return CategoryInsertDTO.builder()
                .name(categoryName)
                .description(categoryDescription)
                .build();
    }

    public static CategoryUpdateDTO createCategoryUpdateDTO(String categoryName, String categoryDescription) {
        return CategoryUpdateDTO.builder()
                .name(categoryName)
                .description(categoryDescription)
                .build();
    }
}
