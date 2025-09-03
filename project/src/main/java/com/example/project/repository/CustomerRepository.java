package com.example.project.repository;

import com.example.project.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customers, Long> {
    Optional<Customers> findByEmail(String email);
}