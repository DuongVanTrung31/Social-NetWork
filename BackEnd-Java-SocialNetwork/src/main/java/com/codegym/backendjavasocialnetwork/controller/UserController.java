package com.codegym.backendjavasocialnetwork.controller;

import com.codegym.backendjavasocialnetwork.entity.User;
import com.codegym.backendjavasocialnetwork.entity.dto.ChangePasswordForm;
import com.codegym.backendjavasocialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/changePassword/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PutMapping("{id}")
    public ResponseEntity<?> changePassword(@PathVariable("id") Long id, @RequestBody ChangePasswordForm changePassword) {
        User user = new User();
        if (userService.findById(id).isPresent()){
            user = userService.findById(id).get();
        }
        if (!passwordEncoder.matches(changePassword.getCurrentPassword(), user.getPassword())) {
//            Mã 600 là lỗi sai mật khẩu hiện tại
            return new ResponseEntity<>(600, HttpStatus.BAD_REQUEST);
        } else if (!changePassword.getNewPassword().equals(changePassword.getConfirmNewPassword())) {
//            Mã 601 là lỗi xác nhận mật khẩu mới sai
            return new ResponseEntity<>(601, HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
