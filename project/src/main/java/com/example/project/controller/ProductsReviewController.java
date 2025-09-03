package com.example.project.controller;

import com.example.project.entity.ProductsReview;
import com.example.project.repository.ProductsReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Adjust origin as needed
public class ProductsReviewController {

    private final ProductsReviewRepository productsReviewRepository;

    @Autowired
    public ProductsReviewController(ProductsReviewRepository productsReviewRepository) {
        this.productsReviewRepository = productsReviewRepository;
    }

    @GetMapping("/productreviews")
    public List<ProductsReview> getAllReviews() {
        return productsReviewRepository.findAll();
    }
}