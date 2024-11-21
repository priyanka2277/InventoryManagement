package com.example.skgsss.service;

import com.example.skgsss.dto.ProductDto;
import com.example.skgsss.entity.Category;
import com.example.skgsss.entity.Product;
import com.example.skgsss.entity.Supplier;
import com.example.skgsss.enums.ProductStatus;
import com.example.skgsss.Repository.CategoryRepository;
import com.example.skgsss.Repository.ProductRepository;
import com.example.skgsss.Repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public ProductDto createProduct(ProductDto productDto) {
        Product product = mapToEntity(productDto);
        Product savedProduct = productRepository.save(product);
        return mapToDto(savedProduct);
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        return mapToDto(product);
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        existingProduct.setName(productDto.getName());
        existingProduct.setLocation(productDto.getLocation());
        existingProduct.setStatus(productDto.getStatus());
        existingProduct.setQuantity(productDto.getQuantity());

        if (productDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + productDto.getCategoryId()));
            existingProduct.setCategory(category);
        }

        if (productDto.getSupplierIds() != null) {
            Set<Supplier> suppliers = supplierRepository.findAllById(productDto.getSupplierIds())
                    .stream().collect(Collectors.toSet());
            existingProduct.setSuppliers(suppliers);
        }

        Product updatedProduct = productRepository.save(existingProduct);
        return mapToDto(updatedProduct);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        productRepository.delete(product);
    }

    private Product mapToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setLocation(productDto.getLocation());
        product.setStatus(productDto.getStatus() != null ? productDto.getStatus() : ProductStatus.IN_STOCK);
        product.setQuantity(productDto.getQuantity());

        if (productDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + productDto.getCategoryId()));
            product.setCategory(category);
        }

        if (productDto.getSupplierIds() != null) {
            Set<Supplier> suppliers = supplierRepository.findAllById(productDto.getSupplierIds())
                    .stream().collect(Collectors.toSet());
            product.setSuppliers(suppliers);
        }

        return product;
    }

    private ProductDto mapToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setLocation(product.getLocation());
        productDto.setStatus(product.getStatus());
        productDto.setQuantity(product.getQuantity());
        productDto.setCategoryId(product.getCategory() != null ? product.getCategory().getId() : null);
        productDto.setSupplierIds(product.getSuppliers() != null
                ? product.getSuppliers().stream().map(Supplier::getId).collect(Collectors.toSet())
                : null);

        return productDto;
    }
}
