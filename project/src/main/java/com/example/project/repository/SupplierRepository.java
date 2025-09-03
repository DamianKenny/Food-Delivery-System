package com.example.project.repository;

import com.example.project.entity.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Suppliers, Long> {

    @Procedure(name = "save_supplier")
    Long saveSupplier(
            @Param("p_name") String name,
            @Param("p_contact_email") String contactEmail,
            @Param("p_phone") String phone,
            @Param("p_address") String address,
            @Param("p_city") String city,
            @Param("p_country") String country
    );
}