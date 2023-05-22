package com.facens.apimarketplace.domain.service.impl;

import com.facens.apimarketplace.application.dto.category.CategoryDTO;
import com.facens.apimarketplace.application.dto.category.CategoryInsertDTO;
import com.facens.apimarketplace.application.dto.category.CategoryUpdateDTO;
import com.facens.apimarketplace.application.exception.BadRequestException;
import com.facens.apimarketplace.domain.factories.CategoryFatory;
import com.facens.apimarketplace.domain.entities.Category;
import com.facens.apimarketplace.domain.repository.CategoryRepository;
import com.facens.apimarketplace.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    private String notFoundMessage = "Não existe a categoria na base de dados.";
    private String existingProductdMessage = "Já existe categoria com esse nome na base de dados.";

    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categories = repository.findAll();
        return categories.stream().map(CategoryFatory::createFromModel).toList();
    }

    @Override
    public CategoryDTO getCategoryById(UUID id) {
        Category category = repository.findById(id).orElseThrow(() -> new BadRequestException(notFoundMessage, BAD_REQUEST));
        return CategoryFatory.createFromModel(category);
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        Optional<Category> category = repository.findByName(name);
        return category.map(CategoryFatory::createFromModel).orElse(null);
    }

    @Override
    public CategoryDTO saveCategory(CategoryInsertDTO categoryInsertDTO) {
        validateProduct(categoryInsertDTO.getName());
        Category category = CategoryFatory.createFromInsertDTO(categoryInsertDTO);
        Category categorySave = repository.save(category);
        return CategoryFatory.createFromModel(categorySave);
    }

    @Override
    public CategoryDTO updateCategoryById(UUID id, CategoryUpdateDTO categoryUpdateDTO) {
        CategoryDTO existingCategory = getCategoryById(id);
        Category category = CategoryFatory.createCategoryUpdateDTO(categoryUpdateDTO, existingCategory);
        Category categorySave = repository.save(category);
        return CategoryFatory.createFromModel(categorySave);
    }

    @Override
    public void deleteCategoryById(UUID id) {
        CategoryDTO categoryDTO = getCategoryById(id);
        repository.deleteById(categoryDTO.getId());
    }

    public void validateProduct(String name){
        CategoryDTO existingCategory  = getCategoryByName(name);
        if(existingCategory != null){
            throw new BadRequestException(existingProductdMessage, BAD_REQUEST);
        }
    }
}
