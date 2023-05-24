package com.facens.apimarketplace.application.controller;

import com.facens.apimarketplace.application.dto.category.CategoryDTO;
import com.facens.apimarketplace.application.dto.category.CategoryInsertDTO;
import com.facens.apimarketplace.application.dto.category.CategoryUpdateDTO;
import com.facens.apimarketplace.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        List<CategoryDTO> categories = service.getCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable UUID id){
        CategoryDTO category = service.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody @Validated CategoryInsertDTO categoryInsertDTO){
        CategoryDTO category = service.saveCategory(categoryInsertDTO);
        return ResponseEntity.ok(category);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategoryById(@PathVariable UUID id, @RequestBody @Validated CategoryUpdateDTO categoryUpdateDTO){
        CategoryDTO category = service.updateCategoryById(id, categoryUpdateDTO);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable UUID id){
        service.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }
    

}
