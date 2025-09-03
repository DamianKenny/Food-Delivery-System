package com.example.project.service;

import com.example.project.entity.Suppliers;
import com.example.project.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuppliersService {

    private final SupplierRepository supplierRepository;

    public SuppliersService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Suppliers> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Suppliers addSupplier(Suppliers supplier) {
        return supplierRepository.save(supplier);
    }
}