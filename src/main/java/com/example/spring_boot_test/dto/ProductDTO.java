package com.example.spring_boot_test.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private Date createdAt;
    private String productName;
    private String productType;
    private String description;
    private BigDecimal price;
    private int quantity;
    private String status;
    private byte[] imageUrl;
    private String imageUrlString;
}
