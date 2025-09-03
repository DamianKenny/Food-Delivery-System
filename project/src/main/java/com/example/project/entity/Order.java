package com.example.project.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ORDER_DATE")
    private LocalDateTime orderDate;

    @Column(name = "PAYMENT_METHOD")
    private String paymentMethod;

    @Column(name = "TOTAL")
    private Double total;

    @Column(name = "TOTAL_ITEMS")
    private Integer totalItems;

    @Lob
    @Column(name = "PRODUCTS")
    private String productsJson;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public Integer getTotalItems() { return totalItems; }
    public void setTotalItems(Integer totalItems) { this.totalItems = totalItems; }
    public String getProductsJson() { return productsJson; }
    public void setProductsJson(String productsJson) { this.productsJson = productsJson; }
}