package com.example.spring_boot_test.service;

import com.example.spring_boot_test.common.StatusUser;
import com.example.spring_boot_test.dto.ChangePassword;
import com.example.spring_boot_test.dto.UserDTO;
import com.example.spring_boot_test.models.User;
import com.example.spring_boot_test.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(User user) {
        Optional<User> userOptional = userRepository.getFirstByUserName(user.getUserName());
        if (userOptional.isPresent()) {
            throw new RuntimeException("Tên tài khoản đã tồn tại");
        }
        if (user.getUserName().length() < 3 || user.getUserName().length() > 16) {
            throw new RuntimeException("Tên đăng nhập phải lớn hơn 3 kí tự hoặc nhỏ hơn 16 kí tự");
        }
        if (user.getPassword().length() < 3 || user.getPassword().length() > 16) {
            throw new RuntimeException("Mật khảu lớn hơn 3 kí tự hoặc nhỏ hơn 16 kí tự");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new RuntimeException("Mật khẩu và xác nhận lại mật khẩu không trùng khớp");
        }
        if (user.getContactNumber().length() != 10) {
            throw new RuntimeException("Số điện thoại không đúng");
        }
        user.setStatus(StatusUser.ACTIVE.toString());
        user.setCreatedAt(new Date());
        userRepository.save(user);
    }

    @Override
    public User updateUser(Long idUser, User user) {
        User userUpdate = checkUser(idUser);
        boolean isUpdate = false;
        if (!userUpdate.getContactNumber().equals(user.getContactNumber())) {
            userUpdate.setContactNumber(user.getContactNumber());
            isUpdate = true;
        }
        if (!userUpdate.getEmail().equals(user.getEmail())) {
            userUpdate.setEmail(user.getEmail());
            isUpdate = true;
        }
        if (userUpdate.getAge() != user.getAge()) {
            userUpdate.setAge(user.getAge());
            isUpdate = true;
        }
        if (isUpdate) {
            userUpdate.setUpdatedAt(new Date());
            return userRepository.save(userUpdate);
        }
        return userUpdate;
    }

    @Override
    public void updateStatusUser(Long idUser, String status) {
        User user = checkUser(idUser);
        user.setStatus(status);
        user.setUpdatedAt(new Date());
        userRepository.save(user);
    }

    @Override
    public UserDTO getDetailUser(Long idUser) {
        User user = checkUser(idUser);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public User checkUser(Long idUser) {
        Optional<User> userOptional = userRepository.findById(idUser);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Không tìm thấy người dùng");
        }
        return userOptional.get();
    }

    @Override
    public void changePassword(ChangePassword changePassword) {
        User userUpdate = checkUser(changePassword.getIdUser());
        if (!changePassword.getOldPassword().equals(userUpdate.getPassword())) {
            throw new RuntimeException("Mật khẩu không đúng");
        }
        if (changePassword.getNewPassword().length() < 3 || changePassword.getNewPassword().length() > 16) {
            throw new RuntimeException("Mật khảu lớn hơn 3 kí tự hoặc nhỏ hơn 16 kí tự");
        }
        if (!changePassword.getNewPassword().equals(changePassword.getConfirmNewPassword())) {
            throw new RuntimeException("Mật khẩu và xác nhận lại mật khẩu không trùng khớp");
        }
        userUpdate.setUpdatedAt(new Date());
        userUpdate.setPassword(changePassword.getNewPassword());
        userUpdate.setConfirmPassword(changePassword.getConfirmNewPassword());
        userRepository.save(userUpdate);
    }

    @Override
    public User login(String username, String password) {
        Optional<User> userOptional = userRepository.getFirstByUserName(username);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Tài khoản không tồn tại");
        }
        if (!password.equals(userOptional.get().getPassword())) {
            throw new RuntimeException("Mật khẩu không đúng");
        }
        if (StatusUser.BANNED.name().equalsIgnoreCase(userOptional.get().getStatus())) {
            throw new RuntimeException("Tài khoản của bạn đã bị khóa vui lòng liên hệ admin để xử lý");
        }
        return userOptional.get();
    }
}
