package com.example.project.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ProductsReview")
public class ProductsReview {
    @Id
    private String id;
    private String product;
    private String userId;
    private int rating;
    private String comment;

    // Constructors, getters, and setters
    public ProductsReview() {}

    public ProductsReview(String product, String userId, int rating, String comment) {
        this.product = product;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }

    // Getters and setters for all fields
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}