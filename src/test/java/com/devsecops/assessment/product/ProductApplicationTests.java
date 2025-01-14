package com.devsecops.assessment.product;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import com.devsecops.assessment.product.entity.Product;
import com.devsecops.assessment.product.repository.ProductRepository;
import com.devsecops.assessment.product.service.ProductService;
import org.springframework.dao.DataIntegrityViolationException;
class ProductApplicationTests {

    private final ProductRepository productoRepository = mock(ProductRepository.class);
    private final ProductService  productoService = new ProductService(productoRepository);

	@Test
	void testObtenerProductoPorId_ProductoExistente() {
		Product producto = new Product();
		producto.setId(1L);
		producto.setName("Producto A");
		producto.setCode("P001");

		when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

		
		ResponseEntity<?> respuesta = productoService.obtenerProduct(1L);

		assertEquals(HttpStatus.OK, respuesta.getStatusCode());
		assertTrue(respuesta.getBody() instanceof Optional); // Verifica que el cuerpo sea un Optional
		Optional<Product> productoEncontrado = (Optional<Product>) respuesta.getBody();
		assertTrue(productoEncontrado.isPresent());
		assertEquals("Producto A", productoEncontrado.get().getName());
		assertEquals("P001", productoEncontrado.get().getCode());
	}

	@Test
	void testObtenerProductoPorId_ProductoNoExistente() {
		// Arrange
		when(productoRepository.findById(1L)).thenReturn(Optional.empty());

		// Act
		ResponseEntity<?> respuesta = productoService.obtenerProduct(1L);

		// Assert
		assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());
		assertNull(respuesta.getBody()); // El cuerpo debe ser nulo en este caso
	}
	
}

