package com.facens.apimarketplace.domain.factories;

import com.facens.apimarketplace.application.dto.category.CategoryDTO;
import com.facens.apimarketplace.application.dto.category.CategoryInsertDTO;
import com.facens.apimarketplace.application.dto.category.CategoryUpdateDTO;
import com.facens.apimarketplace.domain.entities.Category;

import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

public class CategoryFactory {

    public static Category createFromInsertDTO(CategoryInsertDTO categoryInsertDTO){
        return Category.builder()
                .name(categoryInsertDTO.getName())
                .description(categoryInsertDTO.getDescription())
                .creationDate(LocalDateTime.now())
                .build();
    }

    public static CategoryDTO createFromModel(Category category){
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .creationDate(category.getCreationDate())
                .build();
    }

    public static Category createCategoryUpdateDTO(CategoryUpdateDTO categoryUpdateDTO, CategoryDTO existingCategory) {
        return Category.builder()
                .id(existingCategory.getId())
                .name(nonNull(categoryUpdateDTO.getName()) ? categoryUpdateDTO.getName() : existingCategory.getName())
                .description(nonNull(categoryUpdateDTO.getDescription()) ? categoryUpdateDTO.getDescription() : existingCategory.getDescription())
                .creationDate(existingCategory.getCreationDate())
                .build();
    }

    public static Category createFromCategoryDTO(CategoryDTO categoryDTO) {
        return Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .creationDate(categoryDTO.getCreationDate())
                .build();
    }
}
