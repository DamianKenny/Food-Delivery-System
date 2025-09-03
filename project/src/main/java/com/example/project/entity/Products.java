package com.example.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Base64;

@Entity
@Table(name = "PRODUCTS")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCTID")
    private Long productId;

    @Column(name = "NAME")
    @JsonProperty("productName")
    private String name;

    @Lob
    @Column(name = "PRODUCTIMAGE")
    private byte[] productImage;

    @Column(name = "IMAGE_TYPE")
    private String imageType;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "STOCK")
    private Integer stock;

    @JsonIgnore
    public byte[] getProductImage64() {
        return productImage;
    }

    @JsonProperty("productImage")
    public String getProductImageBase64() {
        if (productImage == null) return null;
        return Base64.getEncoder().encodeToString(productImage);
    }


    // Getters and Setters
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public byte[] getProductImage() { return productImage; }
    public void setProductImage(byte[] productImage) { this.productImage = productImage; }

    public String getImageType() { return imageType; }
    public void setImageType(String imageType) { this.imageType = imageType; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

}