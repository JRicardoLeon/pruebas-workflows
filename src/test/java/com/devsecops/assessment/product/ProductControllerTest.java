package com.devsecops.assessment.product;

import com.devsecops.assessment.product.controller.ProductController;
import com.devsecops.assessment.product.entity.Product;
import com.devsecops.assessment.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat; 
import java.util.Optional;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean // Now using the correct import
    private ProductService productoService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testBuscarProductFound() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Producto A");
        product.setCode("P001");

        when(productoService.obtenerProduct(1L)).thenReturn(ResponseEntity.ok(Optional.of(product)));

        mockMvc.perform(get("/product/buscarproduct/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Producto A"))
                .andExpect(jsonPath("$.code").value("P001"));
    }
    
    @Test
    void testBuscarProductNotFound() throws Exception {
        when(productoService.obtenerProduct(1L)).thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty()));

        mockMvc.perform(get("/buscarproduct/{id}", 1L))
                .andExpect(status().isNotFound());
    }
}
