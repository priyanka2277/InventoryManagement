package com.example.skgsss.dto;

import com.example.skgsss.entity.Category;
import com.example.skgsss.entity.Supplier;
import com.example.skgsss.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String location;
    private Integer quantity;
    private ProductStatus status;
    private Long categoryId;
    private Set<Long> supplierIds;

}


