package com.example.project.controller;

import com.example.project.entity.Products;
import com.example.project.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    // Get all products
    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts() {
        try {
            List<Products> products = productsService.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Search by name
    @GetMapping("/search/name")
    public ResponseEntity<List<Products>> searchByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(productsService.searchByName(name));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Search by category
    @GetMapping("/search/category")
    public ResponseEntity<List<Products>> searchByCategory(@RequestParam String category) {
        try {
            return ResponseEntity.ok(productsService.searchByCategory(category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Add product
    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestParam("productName") String name,
                                           @RequestParam("category") String category,
                                           @RequestParam("price") Double price,
                                           @RequestParam("stock") Integer stock,
                                           @RequestParam("productImage") MultipartFile imageFile) {
        try {
            productsService.addProduct(name, category, price, stock, imageFile.getBytes(), imageFile.getContentType());
            return ResponseEntity.ok().build();
        } catch (IOException | SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
