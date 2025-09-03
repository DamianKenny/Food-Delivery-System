    package com.example.project.repository;

    import com.example.project.entity.Products;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.util.List;

    public interface ProductRepository extends JpaRepository<Products, Long> {

        List<Products> findByNameContainingIgnoreCase(String name);

        List<Products> findByCategoryContainingIgnoreCase(String category);
    }
