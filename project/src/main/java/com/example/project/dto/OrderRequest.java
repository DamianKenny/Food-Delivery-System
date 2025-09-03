package com.example.project.dto;

import java.util.List;

public class OrderRequest {
    private String paymentMethod;
    private List<OrderItemDTO> items;
    private String date;

    // Getters and setters
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public List<OrderItemDTO> getItems() { return items; }
    public void setItems(List<OrderItemDTO> items) { this.items = items; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}