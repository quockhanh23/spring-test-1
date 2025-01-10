package com.example.spring_boot_test.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    @Column(name = "product_name")
    private String productName;
    private String productType;
    @Size(min = 0, max = 500)
    private String description;
    private BigDecimal price;
    private int quantity;
    private String status;
    private String imageUrl;
}
