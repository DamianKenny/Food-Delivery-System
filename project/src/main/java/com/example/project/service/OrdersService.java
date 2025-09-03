package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class OrdersService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Add Order procedure call
    public void addOrder(String itemNames, int quantity, double totalAmount, String paymentMethod) throws SQLException {
        String procedureCall = "{call ADD_ORDER(?, ?, ?, ?)}";

        // Calling the procedure using JdbcTemplate
        jdbcTemplate.update(procedureCall, itemNames, quantity, totalAmount, paymentMethod);
    }
}
