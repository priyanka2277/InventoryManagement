package com.example.skgsss.controller;

import com.example.skgsss.dto.ProductDto;
import com.example.skgsss.enums.ProductStatus;
import com.example.skgsss.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductControllerTest {
    @InjectMocks
    private ProductController productController;
    @Mock
    private ProductService productService;
    private ProductDto productDto;
    private List<ProductDto> productDtoList;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
       ProductDto productDto =new ProductDto();
        productDto.setId(1L);
        productDto.setName("woolean sweater");
        productDto.setLocation("Warehouse A");
        productDto.setQuantity(300);
        productDto.setStatus(ProductStatus.IN_STOCK);
        productDto.setCategoryId(1L);
        productDto.setSupplierIds(new HashSet<>(Arrays.asList(1L,2L)));
        productDtoList=Arrays.asList(productDto);
    }
    @Test
    void testCreateProduct(){
        when(productService.createProduct(productDto)).thenReturn(productDto);
        ResponseEntity<ProductDto> response=productController.createProduct(productDto);
        assertEquals(200,response.getStatusCodeValue());
        assertEquals(productDto,response.getBody());
        verify(productService, times(1)).createProduct(productDto);
    }
    @Test
    void testGetProductById(){
        when(productService.getProductById(1L)).thenReturn(productDto);
        ResponseEntity<ProductDto> response=productController.getProductById(1L);
        assertEquals(200,response.getStatusCodeValue());
        assertEquals(productDto,response.getBody());
        verify(productService,times(1)).getProductById(1L);
    }
    @Test
    void testGetAllProducts(){
        when(productService.getAllProducts()).thenReturn(productDtoList);
        ResponseEntity<List<ProductDto>> response=productController.getAllProducts();
        assertEquals(200,response.getStatusCodeValue());
        assertEquals(productDtoList,response.getBody());
        verify(productService,times(1)).getAllProducts();
    }
    @Test
    void testUpdateProduct(){
        ProductDto updatedProductDto=new ProductDto();
        updatedProductDto.setId(1L);
        updatedProductDto.setName("Mobile");
        updatedProductDto.setLocation("Warehouse A");
        updatedProductDto.setQuantity(500);
        updatedProductDto.setStatus(ProductStatus.IN_STOCK);
        updatedProductDto.setCategoryId(2L);
        updatedProductDto.setSupplierIds(new HashSet<>(Arrays.asList(3L,4L)));
        when(productService.updateProduct(1L,productDto)).thenReturn(updatedProductDto);
        ResponseEntity<ProductDto> response=productController.updateProduct(1L,productDto);
        assertEquals(200,response.getStatusCodeValue());
        assertEquals(updatedProductDto,response.getBody());
        verify(productService,times(1)).updateProduct(1L,productDto);
    }
    @Test
    void testDeleteProduct(){
        doNothing().when(productService).deleteProduct(1L);
        ResponseEntity<Void> response=productController.deleteProduct(1L);
        assertEquals(204,response.getStatusCodeValue());
        verify(productService,times(1)).deleteProduct(1L);
    }
}
