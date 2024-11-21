package com.example.skgsss.service;

import com.example.skgsss.dto.CategoryDto;
import com.example.skgsss.entity.Category;
import com.example.skgsss.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        Category savedCategory = categoryRepository.save(category);
        return new CategoryDto(category.getId(),category.getName(),category.getDescription());
    }

    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));
        return new CategoryDto(category.getId(), category.getName(),category.getDescription());
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> new CategoryDto(category.getId(), category.getName(),category.getDescription()))
                .collect(Collectors.toList());
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));

        category.setName(categoryDto.getName());
        Category updatedCategory = categoryRepository.save(category);

        return new CategoryDto(updatedCategory.getId(), updatedCategory.getName(),category.getDescription());
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));
        categoryRepository.delete(category);
    }
}