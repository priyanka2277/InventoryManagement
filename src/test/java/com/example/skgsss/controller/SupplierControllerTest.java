package com.example.skgsss.controller;

import com.example.skgsss.dto.SupplierDto;
import com.example.skgsss.service.SupplierService;
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

public class SupplierControllerTest {
    @InjectMocks
    private SupplierController supplierController;
    @Mock
    private SupplierService supplierService;
    private SupplierDto supplierDto;
    private List<SupplierDto> supplierDtoList;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        SupplierDto supplierDto=new SupplierDto();
        supplierDto.setId(1L);
        supplierDto.setName("ABC supplier.com");
        supplierDto.setContactInfo("abc@gmail.com");
        supplierDtoList = Arrays.asList(supplierDto);

    }
    @Test
    void testCreateSupplier(){
        when(supplierService.createSupplier(supplierDto)).thenReturn(supplierDto);
        ResponseEntity<SupplierDto> response=supplierController.createSupplier(supplierDto);
        assertEquals(200,response.getStatusCodeValue());
        assertEquals(supplierDto,response.getBody());
        verify(supplierService,times(1)).createSupplier(supplierDto);
    }
    @Test
    void testGetSupplierById(){
        when(supplierService.getSupplierById(1L)).thenReturn(supplierDto);
        ResponseEntity<SupplierDto> response=supplierController.getSupplierById(1L);
        assertEquals(200,response.getStatusCodeValue());
        assertEquals(supplierDto,response.getBody());
        verify(supplierService,times(1)).getSupplierById(1L);
    }

    @Test
    void testGetAllSuppliers() {
        when(supplierService.getAllSuppliers()).thenReturn(supplierDtoList);

        ResponseEntity<List<SupplierDto>> response = supplierController.getAllSuppliers();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(supplierDtoList, response.getBody());
        verify(supplierService, times(1)).getAllSuppliers();
    }

    @Test
    void testUpdateSupplier() {
        SupplierDto updatedSupplierDto = new SupplierDto();
        updatedSupplierDto.setId(1L);
        updatedSupplierDto.setName("Updated ABC Supplies");
        updatedSupplierDto.setContactInfo("abc2277@gmail.com");

        when(supplierService.updateSupplier(1L, supplierDto)).thenReturn(updatedSupplierDto);

        ResponseEntity<SupplierDto> response = supplierController.updateSupplier(1L, supplierDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedSupplierDto, response.getBody());
        verify(supplierService, times(1)).updateSupplier(1L, supplierDto);
    }

    @Test
    void testDeleteSupplier() {
        doNothing().when(supplierService).deleteSupplier(1L);

        ResponseEntity<Void> response = supplierController.deleteSupplier(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(supplierService, times(1)).deleteSupplier(1L);
    }

}
