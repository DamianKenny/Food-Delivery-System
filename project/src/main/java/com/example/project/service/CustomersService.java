package com.example.project.service;

import com.example.project.entity.Customers;
import com.example.project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomersService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customers registerCustomer(Customers customer) {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        return customerRepository.save(customer); // Password stored as-is
    }

    public Customers authenticateCustomer(String email, String password) {
        Customers customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!password.equals(customer.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return customer;
    }
}