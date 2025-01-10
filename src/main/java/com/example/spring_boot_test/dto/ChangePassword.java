package com.example.spring_boot_test.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangePassword {
    private Long idUser;
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
}
