package com.example.spring_boot_test.service;

import com.example.spring_boot_test.dto.ChangePassword;
import com.example.spring_boot_test.dto.UserDTO;
import com.example.spring_boot_test.models.User;

public interface UserService {

    void createUser(User user);

    User updateUser(Long idUser, User user);

    void updateStatusUser(Long idUser, String status);

    UserDTO getDetailUser(Long idUser);

    User checkUser(Long idUser);

    void changePassword(ChangePassword changePassword);

    User login(String username, String password);
}
