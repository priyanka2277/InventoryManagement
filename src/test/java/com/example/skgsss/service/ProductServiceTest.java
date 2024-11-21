package com.example.skgsss.service;

import com.example.skgsss.Repository.CategoryRepository;
import com.example.skgsss.Repository.ProductRepository;
import com.example.skgsss.Repository.SupplierRepository;
import com.example.skgsss.dto.ProductDto;
import com.example.skgsss.entity.Product;
import com.example.skgsss.enums.ProductStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private SupplierRepository supplierRepository;
    @InjectMocks
    private ProductService productService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testCreateProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setName("Test Product");
        productDto.setLocation("Warehouse A");
        productDto.setQuantity(100);
        productDto.setStatus(ProductStatus.IN_STOCK);

        Product product = new Product();
        product.setName(productDto.getName());
        product.setLocation(productDto.getLocation());
        product.setQuantity(productDto.getQuantity());
        product.setStatus(productDto.getStatus());

        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDto createdProduct = productService.createProduct(productDto);

        assertNotNull(createdProduct);
        assertEquals(productDto.getName(), createdProduct.getName());
        assertEquals(productDto.getLocation(), createdProduct.getLocation());
        assertEquals(productDto.getStatus(), createdProduct.getStatus());
        verify(productRepository, times(1)).save(any(Product.class));
    }
        @Test
        void testGetProductById() {
            Long productId = 1L;
            Product product = new Product();
            product.setId(productId);
            product.setName("Test Product");
            product.setLocation("Warehouse B");

            when(productRepository.findById(productId)).thenReturn(Optional.of(product));

            ProductDto productDto = productService.getProductById(productId);

            assertNotNull(productDto);
            assertEquals(product.getName(), productDto.getName());
            assertEquals(product.getLocation(), productDto.getLocation());
            verify(productRepository, times(1)).findById(productId);
        }
    @Test
    void testGetAllProducts() {
        Product product1 = new Product();
        product1.setName("Mobile");

        Product product2 = new Product();
        product2.setName("Laptop");

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<ProductDto> productDtos = productService.getAllProducts();

        assertEquals(2, productDtos.size());
        assertEquals("Mobile", productDtos.get(0).getName());
        assertEquals("Laptop", productDtos.get(1).getName());
        verify(productRepository, times(1)).findAll();
    }
    @Test
    void testUpdateProduct() {
        Long productId = 1L;
        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setName("Mobile");
        existingProduct.setLocation("Warehouse C");

        ProductDto productDto = new ProductDto();
        productDto.setName("Laptop");
        productDto.setLocation("Warehouse D");
        productDto.setQuantity(50);
        productDto.setStatus(ProductStatus.OUT_OF_STOCK);

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

        ProductDto updatedProduct = productService.updateProduct(productId, productDto);

        assertNotNull(updatedProduct);
        assertEquals(productDto.getName(), updatedProduct.getName());
        assertEquals(productDto.getLocation(), updatedProduct.getLocation());
        verify(productRepository, times(1)).save(any(Product.class));
    }
    @Test
    void testDeleteProduct() {
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).delete(product);

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).delete(product);
    }

}
