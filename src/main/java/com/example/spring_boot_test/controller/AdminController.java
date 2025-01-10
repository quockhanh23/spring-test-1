package com.example.spring_boot_test.controller;

import com.example.spring_boot_test.dto.UserDTO;
import com.example.spring_boot_test.models.User;
import com.example.spring_boot_test.repository.UserRepository;
import com.example.spring_boot_test.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/actions")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get-all-user")
    public ResponseEntity<?> getDetailUser(@RequestParam Long idAdmin) {
        userService.getDetailUser(idAdmin);
        List<User> userList = userRepository.findAll();
        userList.removeIf((n -> (n.getId().equals(idAdmin))));
        List<UserDTO> userDTOList = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userList.get(i), userDTO);
            userDTOList.add(userDTO);
        }
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @GetMapping("/update-status")
    public ResponseEntity<?> updateStatus(@RequestParam Long idAdmin, @RequestParam Long idUser,
                                          @RequestParam String status) {
        userService.getDetailUser(idAdmin);
        userService.getDetailUser(idUser);
        userService.updateStatusUser(idUser, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
