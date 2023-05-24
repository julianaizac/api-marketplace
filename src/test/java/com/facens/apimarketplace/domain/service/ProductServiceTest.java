package com.facens.apimarketplace.domain.service;

import com.facens.apimarketplace.application.dto.product.ProductDTO;
import com.facens.apimarketplace.application.exception.BadRequestException;
import com.facens.apimarketplace.infrastructure.repository.ProductRepository;
import com.facens.apimarketplace.infrastructure.repository.StockRepository;
import com.facens.apimarketplace.domain.service.impl.ProductServiceImpl;
import com.facens.apimarketplace.mocks.MocksCategory;
import com.facens.apimarketplace.mocks.MocksProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static com.facens.apimarketplace.mocks.MocksCategory.*;
import static com.facens.apimarketplace.mocks.MocksProduct.*;
import static com.facens.apimarketplace.mocks.MocksStock.STOCK_AMOUNT;
import static com.facens.apimarketplace.mocks.MocksStock.STOCK_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @InjectMocks
    @Spy
    private ProductServiceImpl service;

    @Mock
    private ProductRepository repository;

    @Mock
    private CategoryService categoryService;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(service, "repository", repository);
        ReflectionTestUtils.setField(service, "categoryService", categoryService);
        ReflectionTestUtils.setField(service, "stockRepository", stockRepository);
    }

    @DisplayName("Deve buscar produtos")
    @Test
    void whenGetProductsThenReturnsEmptyList(){
        when(repository.findAll()).thenReturn(new ArrayList<>());
        List<ProductDTO> products = service.getProducts();
        assertEquals(new ArrayList<>(), products);
    }

    @DisplayName("Deve buscar produto por id")
    @Test
    void whenGetProductByIdThenReturnProduct(){
        when(repository.findById(PRODUCT_ID)).thenReturn(Optional.ofNullable(
                MocksProduct.createProduct(PRODUCT_ID, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE,
                        CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION, STOCK_ID, STOCK_AMOUNT)));

        ProductDTO product = service.getProductById(PRODUCT_ID);
        assertTrue(Objects.nonNull(product));
    }

    @DisplayName("Deve buscar produto por nome e lançar BadRequestException")
    @Test
    void whenGetProductByNameThenThrowBadRequestException(){
        when(repository.findByName(PRODUCT_NAME)).thenReturn(Optional.empty());

        BadRequestException exception = assertThrows(BadRequestException.class, () -> service.getProductById(PRODUCT_ID));

        assertEquals("Não existe o produto na base de dados.", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(repository, times(1)).findById(PRODUCT_ID);
    }

    @DisplayName("Deve salvar produto")
    @Test
    void whenSaveProductThenReturnProduct(){
        when(repository.findByName(PRODUCT_NAME)).thenReturn(Optional.empty());
        when(categoryService.getCategoryById(CATEGORY_ID)).thenReturn(MocksCategory.createCategoryDTO(CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION));

        when(repository.save(any()))
                .thenReturn(MocksProduct.createProduct(PRODUCT_ID, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE,
                CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION, STOCK_ID, STOCK_AMOUNT));

        ProductDTO product = service.saveProduct(MocksProduct.createProductInsertDTO(PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE, CATEGORY_ID));
        assertEquals(PRODUCT_ID, product.getId());
        Mockito.verify(repository, times(1)).save(any());
    }

    @DisplayName("Deve atualizar produto por id")
    @Test
    void whenUpdateProductByIdThenReturnProduct(){
        when(repository.findById(PRODUCT_ID))
                .thenReturn(Optional.ofNullable(MocksProduct.createProduct(PRODUCT_ID, PRODUCT_NAME, PRODUCT_DESCRIPTION,
                        PRODUCT_PRICE, CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION, STOCK_ID, STOCK_AMOUNT)));
        when(repository.save(any()))
                .thenReturn(MocksProduct.createProduct(PRODUCT_ID, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE,
                        CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION, STOCK_ID, STOCK_AMOUNT));
        ProductDTO product = service.updateProductById(PRODUCT_ID,
                MocksProduct.createProductUpdateDTO(PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE));
        assertEquals(PRODUCT_ID, product.getId());
        verify(repository, times(1)).save(any());
    }

    @DisplayName("Deve deletar produto por id")
    @Test
    void whenDeleteProductById(){
        when(repository.findById(PRODUCT_ID)).thenReturn(Optional.ofNullable(
                MocksProduct.createProduct(PRODUCT_ID, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE,
                        CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION, STOCK_ID, STOCK_AMOUNT)));

        service.deleteProductById(PRODUCT_ID);

        verify(repository, times(1)).findById(PRODUCT_ID);
        verify(repository, times(1)).deleteById(PRODUCT_ID);
    }


}
