package com.example.project.repository;

import com.example.project.entity.ProductsReview;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProductsReviewRepository extends MongoRepository<ProductsReview, String> {
    List<ProductsReview> findAll();
}