package com.example.skgsss.service;
import com.example.skgsss.Repository.CategoryRepository;
import com.example.skgsss.dto.CategoryDto;
import com.example.skgsss.entity.Category;
import com.example.skgsss.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*; // Correct Mockito import
import static org.junit.jupiter.api.Assertions.*;

public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;
    private CategoryDto categoryDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        category = new Category();
        category.setId(1L);
        category.setName("Electronics");
        category.setDescription("Category for electronic items");

        categoryDto = new CategoryDto(1L, "Electronics", "Category for electronic items");
    }

    @Test
    void testCreateCategory() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryDto result = categoryService.createCategory(categoryDto);

        assertNotNull(result);
        assertEquals(categoryDto.getName(), result.getName());
        assertEquals(categoryDto.getDescription(), result.getDescription());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }
    @Test
    void testGetCategoryById(){
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        CategoryDto result=categoryService.getCategoryById(1L);
        assertNotNull(result);
        assertEquals(category.getId(),result.getId());
        assertEquals(category.getName(),result.getName());
        assertEquals(category.getDescription(),result.getDescription());
    }
    @Test
    void testGetAllCategories(){
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category));
        List<CategoryDto> result=categoryService.getAllCategories();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        assertEquals(category.getName(),result.get(0).getName());
    }
    @Test
    void testDeleteCategory(){
        Long id=1L;
        Category category=new Category();
        category.setId(id);
        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        assertDoesNotThrow(()->categoryService.deleteCategory(id));
        verify(categoryRepository,times(1)).delete(category);
    }


}
