package com.example.skgsss.controller;

import com.example.skgsss.dto.CategoryDto;
import com.example.skgsss.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryControllerTest {
    @InjectMocks
    private CategoryController categoryController;
    @Mock
    private CategoryService categoryService;
    private CategoryDto categoryDto;
    private List<CategoryDto> categoryDtoList;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setId(1L);
        categoryDto.setName("Electronics");
        categoryDto.setDescription("chair,table like furniture");
        categoryDtoList= Arrays.asList(categoryDto);
    }
    @Test
    void testCreateCategory(){
        when(categoryService.createCategory(categoryDto)).thenReturn(categoryDto);
        ResponseEntity<CategoryDto> response=categoryController.createCategory(categoryDto);
        assertEquals(200,response.getStatusCodeValue());
        assertEquals(categoryDto,response.getBody());
        verify(categoryService,times(1)).createCategory(categoryDto);
    }
    @Test
    void testGetCategoryById(){
        when(categoryService.getCategoryById(1L)).thenReturn(categoryDto);
        ResponseEntity<CategoryDto> response=categoryController.getCategoryById(1L);
        assertEquals(200,response.getStatusCodeValue());
        assertEquals(categoryDto,response.getBody());
        verify(categoryService,times(1)).getCategoryById(1L);
    }
    @Test
    void testGetAllCategories(){
        when(categoryService.getAllCategories()).thenReturn(categoryDtoList);
        ResponseEntity<List<CategoryDto>> response=categoryController.getAllCategories();
        assertEquals(200,response.getStatusCodeValue());
        assertEquals(categoryDtoList,response.getBody());
        verify(categoryService,times(1)).getAllCategories();
    }
    @Test
    void testUpdateCategory(){
        CategoryDto updatedCategoryDto=new CategoryDto();
        updatedCategoryDto.setId(1L);
        updatedCategoryDto.setName("Updated Electronics");
        updatedCategoryDto.setDescription(" Mobile,Television,Laptop");
        when(categoryService.updateCategory(1L,categoryDto)).thenReturn(updatedCategoryDto);
        ResponseEntity<CategoryDto> response=categoryController.updateCategory(1L,categoryDto);
        assertEquals(200,response.getStatusCodeValue());
        assertEquals(updatedCategoryDto
        ,response.getBody());
        verify(categoryService,times(1)).updateCategory(1L,categoryDto);
    }
    @Test
    void testDeleteCategory(){
        doNothing().when(categoryService).deleteCategory(1L);
        ResponseEntity<Void> response=categoryController.deleteCategory(1L);
        assertEquals(204,response.getStatusCodeValue());
        verify(categoryService,times(1)).deleteCategory(1L);
    }

}
