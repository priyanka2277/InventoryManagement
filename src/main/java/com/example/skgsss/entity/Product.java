package com.example.skgsss.entity;

import com.example.skgsss.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer quantity;
    private String location;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="category_id",nullable = false)
    private Category category;
    @ManyToMany
    @JoinTable(
            name="product_suppliers",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="supplier_id")
    )
    private Set<Supplier> suppliers;
}
