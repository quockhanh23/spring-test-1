package com.example.spring_boot_test.controller;

import com.example.spring_boot_test.dto.ChangePassword;
import com.example.spring_boot_test.dto.UserDTO;
import com.example.spring_boot_test.models.User;
import com.example.spring_boot_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            userService.createUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User userRequest) {
        try {
            User user = userService.login(userRequest.getUserName(), userRequest.getPassword());
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-information")
    public ResponseEntity<?> updateUser(@RequestParam Long idUser, @RequestBody User user) {
        try {
            User userUpdate = userService.updateUser(idUser, user);
            return new ResponseEntity<>(userUpdate, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword changePassword) {
        try {
            userService.changePassword(changePassword);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-detail")
    public ResponseEntity<?> getDetailUser(@RequestParam Long idUser) {
        UserDTO userDTO = userService.getDetailUser(idUser);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
