package com.example.project.controller;

import com.example.project.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderController orderService;

    @PostMapping
    public ResponseEntity<String> addOrder(
            @RequestParam("itemNames") String itemNames,
            @RequestParam("quantity") int quantity,
            @RequestParam("totalAmount") double totalAmount,
            @RequestParam("paymentMethod") String paymentMethod) {

        try {
            orderService.addOrder(itemNames, quantity, totalAmount, paymentMethod);
            return ResponseEntity.status(HttpStatus.CREATED).body("Order successfully created.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating order: " + e.getMessage());
        }
    }
}
