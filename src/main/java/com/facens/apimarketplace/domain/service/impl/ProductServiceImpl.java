package com.facens.apimarketplace.domain.service.impl;

import com.facens.apimarketplace.application.dto.category.CategoryDTO;
import com.facens.apimarketplace.application.dto.product.ProductDTO;
import com.facens.apimarketplace.application.dto.product.ProductInsertDTO;
import com.facens.apimarketplace.application.dto.product.ProductUpdateDTO;
import com.facens.apimarketplace.application.exception.BadRequestException;
import com.facens.apimarketplace.domain.factories.ProductFatory;
import com.facens.apimarketplace.domain.entities.Product;
import com.facens.apimarketplace.domain.entities.Stock;
import com.facens.apimarketplace.domain.repository.ProductRepository;
import com.facens.apimarketplace.domain.repository.StockRepository;
import com.facens.apimarketplace.domain.service.CategoryService;
import com.facens.apimarketplace.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StockRepository stockRepository;

    private String notFoundMessage = "Não existe o produto na base de dados.";
    private String existingProductdMessage = "Já existe um produto com esse nome na base de dados.";

    @Override
    public List<ProductDTO> getProducts() {
        List<Product> products = repository.findAll();
        return products.stream().map(ProductFatory::createFromModel).toList();
    }

    @Override
    public ProductDTO getProductById(UUID id) {
        Product product = repository.findById(id).orElseThrow(() -> new BadRequestException(notFoundMessage, BAD_REQUEST));
        return ProductFatory.createFromModel(product);
    }

    @Override
    public ProductDTO getProductByName(String name) {
        Optional<Product> product = repository.findByName(name);
        return product.map(ProductFatory::createFromModel).orElse(null);
    }

    @Override
    public ProductDTO saveProduct(ProductInsertDTO productInsertDTO) {
        validateProduct(productInsertDTO.getName());
        CategoryDTO categoryDTO = categoryService.getCategoryById(productInsertDTO.getCategoryId());

        Stock stock = new Stock();
        stock.setAmount(0);
        stock.setCreationDate(LocalDateTime.now());

        Product product = ProductFatory.createFromInsertDTO(productInsertDTO, categoryDTO);

        stock.setProduct(product);
        product.setStock(stock);

        Product productSave = repository.save(product);
        stockRepository.save(stock);

        return ProductFatory.createFromModel(productSave);
    }

    @Override
    public ProductDTO updateProductById(UUID id, ProductUpdateDTO productUpdateDTO) {
        ProductDTO existingProduct  = getProductById(id);
        Product product = ProductFatory.createFromUpdateDTO(productUpdateDTO, existingProduct);
        Product productSave = repository.save(product);
        return ProductFatory.createFromModel(productSave);
    }

    @Override
    public void deleteProductById(UUID id) {
        ProductDTO productDTO = getProductById(id);
        repository.deleteById(productDTO.getId());
    }

    public void validateProduct(String name){
        ProductDTO existingProduct  = getProductByName(name);
        if(existingProduct != null){
            throw new BadRequestException(existingProductdMessage, BAD_REQUEST);
        }
    }
}
