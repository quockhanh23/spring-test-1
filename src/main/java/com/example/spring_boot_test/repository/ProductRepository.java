package com.example.spring_boot_test.repository;

import com.example.spring_boot_test.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByProductName(String productName);

    List<Product> findAllByProductType(String productType);
}