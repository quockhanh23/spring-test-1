package com.example.spring_boot_test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExternalAddress {
    private int total;
    private List<Province> data;
    private String code;
    private String message;
}
