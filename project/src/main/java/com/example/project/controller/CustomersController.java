package com.example.project.controller;

import com.example.project.entity.Customers;
import com.example.project.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow all origins
public class CustomersController {

    @Autowired
    private CustomersService customersService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody Customers customer) {
        try {
            Customers registeredCustomer = customersService.registerCustomer(customer);
            return ResponseEntity.ok(registeredCustomer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            Customers customer = customersService.authenticateCustomer(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            );
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            // Return a consistent error format
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(400).body(errorResponse);
        }
    }

    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}