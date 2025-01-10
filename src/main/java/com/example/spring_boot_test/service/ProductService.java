package com.example.spring_boot_test.service;

import com.example.spring_boot_test.models.Product;

import java.util.List;

public interface ProductService {

    void createProduct(Product product);

    Product updateProduct(Long idProduct, Product product);

    void deleteProduct(Long idProduct);

    List<Product> getAllProduct();

    Product findById(Long idProduct);
}
