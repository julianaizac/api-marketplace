package com.facens.apimarketplace.domain.service;

import com.facens.apimarketplace.application.dto.category.CategoryDTO;
import com.facens.apimarketplace.application.exception.BadRequestException;
import com.facens.apimarketplace.infrastructure.repository.CategoryRepository;
import com.facens.apimarketplace.domain.service.impl.CategoryServiceImpl;
import com.facens.apimarketplace.mocks.MocksCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static com.facens.apimarketplace.mocks.MocksCategory.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @InjectMocks
    @Spy
    private CategoryServiceImpl service;

    @Mock
    private CategoryRepository repository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(service, "repository", repository);
    }

    @DisplayName("Deve buscar categorias")
    @Test
    void whenGetCategoriesThenReturnsEmptyList(){
        when(repository.findAll()).thenReturn(new ArrayList<>());
        List<CategoryDTO> categories = service.getCategories();
        assertEquals(new ArrayList<>(), categories);
    }

    @DisplayName("Deve buscar categoria por id")
    @Test
    void whenGetCategoryByIdThenReturnProduct(){
        when(repository.findById(CATEGORY_ID)).thenReturn(
                Optional.ofNullable(MocksCategory.createCategory(CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION)));
        CategoryDTO category = service.getCategoryById(CATEGORY_ID);
        assertTrue(Objects.nonNull(category));
    }


    @DisplayName("Deve buscar categoria por id e lançar BadRequestException")
    @Test
    void whenGeCategoryByIdThenThrowBadRequestException(){
        when(repository.findById(CATEGORY_ID)).thenReturn(Optional.empty());
        BadRequestException exception = assertThrows(BadRequestException.class, () -> service.getCategoryById(CATEGORY_ID));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(repository, times(1)).findById(CATEGORY_ID);
    }

    @DisplayName("Deve salvar categoria")
    @Test
    void teste4(){
        when(repository.save(Mockito.any())).thenReturn(MocksCategory.createCategory(CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION));
        CategoryDTO category = service.saveCategory(MocksCategory.createCategoryInsertDTO(CATEGORY_NAME, CATEGORY_DESCRIPTION));
        assertEquals(CATEGORY_ID, category.getId());
    }

    @DisplayName("Deve atualizar categoria por id")
    @Test
    void teste5(){
        when(repository.findById(CATEGORY_ID))
                .thenReturn(Optional.ofNullable(MocksCategory.createCategory(CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION)));
        when(repository.save(Mockito.any()))
                .thenReturn(MocksCategory.createCategory(CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION));
        CategoryDTO category = service.updateCategoryById(CATEGORY_ID, MocksCategory.createCategoryUpdateDTO(CATEGORY_NAME, CATEGORY_DESCRIPTION));
        assertEquals(CATEGORY_ID, category.getId());
        verify(repository, times(1)).save(any());
    }

    @DisplayName("Deve deletar categoria por id")
    @Test
    void teste6(){
        when(repository.findById(CATEGORY_ID)).thenReturn(Optional.ofNullable(
                MocksCategory.createCategory(CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION)
        ));

        service.deleteCategoryById(CATEGORY_ID);
        verify(repository, times(1)).findById(CATEGORY_ID);
        verify(repository, times(1)).deleteById(CATEGORY_ID);
    }

}
