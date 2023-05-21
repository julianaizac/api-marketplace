package com.facens.apimarketplace.application.controller;

import com.facens.apimarketplace.application.dto.product.ProductDTO;
import com.facens.apimarketplace.application.dto.product.ProductInsertDTO;
import com.facens.apimarketplace.application.dto.product.ProductUpdateDTO;
import com.facens.apimarketplace.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(){
        List<ProductDTO> products = service.getProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable UUID id){
        ProductDTO product = service.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductInsertDTO productInsertDTO){
        ProductDTO product = service.saveProduct(productInsertDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProductById(@PathVariable UUID id, @RequestBody ProductUpdateDTO productUpdateDTO){
        ProductDTO product = service.updateProductById(id, productUpdateDTO);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable UUID id){
        service.deleteProductById(id);
        return ResponseEntity.ok().build();
    }


}
