package com.example.skgsss.service;

import com.example.skgsss.dto.SupplierDto;
import com.example.skgsss.entity.Supplier;
import com.example.skgsss.Repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public SupplierDto createSupplier(SupplierDto supplierDto) {
        Supplier supplier = new Supplier();
        supplier.setName(supplierDto.getName());
        supplier.setContactInfo(supplierDto.getContactInfo());
        Supplier savedSupplier = supplierRepository.save(supplier);
        return new SupplierDto(savedSupplier.getId(), savedSupplier.getName(), savedSupplier.getContactInfo());
    }

    public SupplierDto getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + id));
        return new SupplierDto(supplier.getId(), supplier.getName(), supplier.getContactInfo());
    }

    public List<SupplierDto> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream()
                .map(supplier -> new SupplierDto(supplier.getId(), supplier.getName(), supplier.getContactInfo()))
                .collect(Collectors.toList());
    }

    public SupplierDto updateSupplier(Long id, SupplierDto supplierDto) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + id));

        supplier.setName(supplierDto.getName());
        supplier.setContactInfo(supplierDto.getContactInfo());
        Supplier updatedSupplier = supplierRepository.save(supplier);

        return new SupplierDto(updatedSupplier.getId(), updatedSupplier.getName(), updatedSupplier.getContactInfo());
    }

    public void deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + id));
        supplierRepository.delete(supplier);
    }
}
