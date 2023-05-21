package com.facens.apimarketplace.domain.service;

import com.facens.apimarketplace.application.dto.category.CategoryDTO;
import com.facens.apimarketplace.application.dto.category.CategoryInsertDTO;
import com.facens.apimarketplace.application.dto.category.CategoryUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<CategoryDTO> getCategories();
    CategoryDTO getCategoryById(UUID id);
    CategoryDTO getCategoryByName(String name);
    CategoryDTO saveCategory(CategoryInsertDTO categoryInsertDTO);
    CategoryDTO updateCategoryById(UUID id, CategoryUpdateDTO categoryUpdateDTO);
    void deleteCategoryById(UUID id);

}
