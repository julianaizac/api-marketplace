package com.facens.apimarketplace.domain.service;

import com.facens.apimarketplace.application.dto.product.ProductDTO;
import com.facens.apimarketplace.application.dto.product.ProductInsertDTO;
import com.facens.apimarketplace.application.dto.product.ProductUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ProductDTO> getProducts();
    ProductDTO getProductById(UUID id);
    ProductDTO getProductByName(String name);
    ProductDTO saveProduct(ProductInsertDTO productInsertDTO);
    ProductDTO updateProductById(UUID id, ProductUpdateDTO productUpdateDTO);
    void deleteProductById(UUID id);
}
