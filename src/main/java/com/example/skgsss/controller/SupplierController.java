package com.example.skgsss.controller;

import com.example.skgsss.dto.SupplierDto;
import com.example.skgsss.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierDto> createSupplier(@RequestBody SupplierDto supplierDto) {
        SupplierDto createdSupplier = supplierService.createSupplier(supplierDto);
        return ResponseEntity.ok(createdSupplier);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDto> getSupplierById(@PathVariable Long id) {
        SupplierDto supplierDto = supplierService.getSupplierById(id);
        return ResponseEntity.ok(supplierDto);
    }

    @GetMapping
    public ResponseEntity<List<SupplierDto>> getAllSuppliers() {
        List<SupplierDto> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDto> updateSupplier(@PathVariable Long id, @RequestBody SupplierDto supplierDto) {
        SupplierDto updatedSupplier = supplierService.updateSupplier(id, supplierDto);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}
