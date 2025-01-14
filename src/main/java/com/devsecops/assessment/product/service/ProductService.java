package com.devsecops.assessment.product.service;


import java.util.Optional;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devsecops.assessment.product.entity.Product;
import com.devsecops.assessment.product.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public ResponseEntity<Optional<Product>> obtenerProduct(Long id){
        if (productRepository.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
     return ResponseEntity.ok(productRepository.findById(id));
    }

}
