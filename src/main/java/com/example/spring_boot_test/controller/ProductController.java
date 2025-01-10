package com.example.spring_boot_test.controller;

import com.example.spring_boot_test.dto.ProductDTO;
import com.example.spring_boot_test.models.Product;
import com.example.spring_boot_test.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/get-all-product")
    public ResponseEntity<?> getAllProduct() {
        try {
            List<Product> products = productService.getAllProduct();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-detail")
    public ResponseEntity<?> getDetailProduct(@RequestParam Long idProduct) {
        try {
            Product product = productService.findById(idProduct);
            byte[] imageByte = Files.readAllBytes(Paths.get(product.getImageUrl()));
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(product, productDTO);
            productDTO.setImageUrl(imageByte);
            productDTO.setImageUrlString(product.getImageUrl());
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-product")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            productService.createProduct(product);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-product")
    public ResponseEntity<?> updateProduct(@RequestParam Long idProduct, @RequestBody Product product) {
        try {

            Product productUpdated = productService.updateProduct(idProduct, product);
            return new ResponseEntity<>(productUpdated, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
