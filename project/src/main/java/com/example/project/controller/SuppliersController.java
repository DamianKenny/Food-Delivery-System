package com.example.project.controller;

import com.example.project.entity.Suppliers;
import com.example.project.service.SuppliersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin(origins = "*") // enable CORS for fetch calls
public class SuppliersController {

    private final SuppliersService suppliersService;

    public SuppliersController(SuppliersService suppliersService) {
        this.suppliersService = suppliersService;
    }

    @GetMapping
    public List<Suppliers> getAllSuppliers() {
        return suppliersService.getAllSuppliers();
    }

    @PostMapping
    public Suppliers createSupplier(@RequestBody Suppliers supplier) {
        return suppliersService.addSupplier(supplier);
    }

}
