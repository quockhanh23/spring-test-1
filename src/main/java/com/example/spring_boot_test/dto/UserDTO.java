package com.example.spring_boot_test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private Date createdAt;
    private String userName;
    private String email;
    private String contactNumber;
    private int age;
    private String status;
}
