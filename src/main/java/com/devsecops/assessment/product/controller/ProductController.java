package com.devsecops.assessment.product.controller;

import com.devsecops.assessment.product.entity.Product;
import com.devsecops.assessment.product.service.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product/")
public class ProductController {
    @Autowired
    private final ProductService productoService;
    public ProductController (ProductService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("buscarproduct/{id}")
    public ResponseEntity<Optional<Product>> buscarProduct(@PathVariable Long id){
        return productoService.obtenerProduct(id);
    }
}
