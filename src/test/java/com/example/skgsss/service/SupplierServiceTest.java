package com.example.skgsss.service;

import com.example.skgsss.Repository.SupplierRepository;
import com.example.skgsss.dto.SupplierDto;
import com.example.skgsss.entity.Supplier;
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

public class SupplierServiceTest {
    @Mock
    private SupplierRepository supplierRepository;
    @InjectMocks
    private SupplierService supplierService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testCreateSupplier() {
        // Arrange
        SupplierDto supplierDto = new SupplierDto(null, "Supplier A", "Contact A");
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        supplier.setName("Supplier A");
        supplier.setContactInfo("Contact A");

        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);

        // Act
        SupplierDto createdSupplier = supplierService.createSupplier(supplierDto);

        // Assert
        assertNotNull(createdSupplier);
        assertEquals(1L, createdSupplier.getId());
        assertEquals("Supplier A", createdSupplier.getName());
        assertEquals("Contact A", createdSupplier.getContactInfo());
        verify(supplierRepository, times(1)).save(any(Supplier.class));
    }
    @Test
    void testGetSupplierById() {
        // Arrange
        Long supplierId = 1L;
        Supplier supplier = new Supplier();
        supplier.setId(supplierId);
        supplier.setName("Supplier A");
        supplier.setContactInfo("Contact A");

        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));

        // Act
        SupplierDto supplierDto = supplierService.getSupplierById(supplierId);

        // Assert
        assertNotNull(supplierDto);
        assertEquals(supplierId, supplierDto.getId());
        assertEquals("Supplier A", supplierDto.getName());
        assertEquals("Contact A", supplierDto.getContactInfo());
        verify(supplierRepository, times(1)).findById(supplierId);
    }

    @Test
    void testGetAllSuppliers() {
        // Arrange
        Supplier supplier1 = new Supplier();
        supplier1.setId(1L);
        supplier1.setName("Supplier A");
        supplier1.setContactInfo("Contact A");

        Supplier supplier2 = new Supplier();
        supplier2.setId(2L);
        supplier2.setName("Supplier B");
        supplier2.setContactInfo("Contact B");

        when(supplierRepository.findAll()).thenReturn(Arrays.asList(supplier1, supplier2));

        // Act
        List<SupplierDto> supplierDtos = supplierService.getAllSuppliers();

        // Assert
        assertNotNull(supplierDtos);
        assertEquals(2, supplierDtos.size());
        assertEquals("Supplier A", supplierDtos.get(0).getName());
        assertEquals("Supplier B", supplierDtos.get(1).getName());
        verify(supplierRepository, times(1)).findAll();
    }

    @Test
    void testUpdateSupplier() {
        // Arrange
        Long supplierId = 1L;
        Supplier existingSupplier = new Supplier();
        existingSupplier.setId(supplierId);
        existingSupplier.setName("ABC Supplier");
        existingSupplier.setContactInfo("abc@gmail.com");

        SupplierDto updatedSupplierDto = new SupplierDto(null, "CDE", "cde@gmail.com");
        Supplier updatedSupplier = new Supplier();
        updatedSupplier.setId(supplierId);
        updatedSupplier.setName("CDE");
        updatedSupplier.setContactInfo("cde@gmail.com");

        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(existingSupplier));
        when(supplierRepository.save(existingSupplier)).thenReturn(updatedSupplier);

        // Act
        SupplierDto result = supplierService.updateSupplier(supplierId, updatedSupplierDto);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Supplier", result.getName());
        assertEquals("Updated Contact", result.getContactInfo());
        verify(supplierRepository, times(1)).findById(supplierId);
        verify(supplierRepository, times(1)).save(existingSupplier);
    }
    @Test
    void testDeleteSupplier() {
        // Arrange
        Long supplierId = 1L;
        Supplier supplier = new Supplier();
        supplier.setId(supplierId);

        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));
        doNothing().when(supplierRepository).delete(supplier);

        // Act
        supplierService.deleteSupplier(supplierId);

        // Assert
        verify(supplierRepository, times(1)).findById(supplierId);
        verify(supplierRepository, times(1)).delete(supplier);
    }

}
