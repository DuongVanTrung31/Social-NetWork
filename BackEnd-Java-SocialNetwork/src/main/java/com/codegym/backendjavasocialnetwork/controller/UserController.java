package com.codegym.backendjavasocialnetwork.controller;

import com.codegym.backendjavasocialnetwork.entity.User;
import com.codegym.backendjavasocialnetwork.entity.dto.ChangePasswordForm;
import com.codegym.backendjavasocialnetwork.entity.dto.UserInfoForm;
import com.codegym.backendjavasocialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PutMapping("changePassword/{id}")
    public ResponseEntity<?> changePassword(@PathVariable("id") Long id, @RequestBody ChangePasswordForm changePassword) {
        User user = new User();
        if (userService.findById(id).isPresent()) {
            user = userService.findById(id).get();
        }
        if (!passwordEncoder.matches(changePassword.getCurrentPassword(), user.getPassword()) &&
                !changePassword.getNewPassword().equals(changePassword.getConfirmNewPassword())) {
//              Mã 600 là lỗi cả 2 comment dưới sai
            return new ResponseEntity<>(600, HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (!changePassword.getNewPassword().equals(changePassword.getConfirmNewPassword())) {
//            Mã 601 là lỗi xác nhận mật khẩu mới sai
            return new ResponseEntity<>(601, HttpStatus.INTERNAL_SERVER_ERROR);
        } else if(!passwordEncoder.matches(changePassword.getCurrentPassword(), user.getPassword())) {
//            Mã 602 là lỗi sai mật khẩu hiện tại
            return new ResponseEntity<>(602, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        user.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateInfo/{id}")
    public ResponseEntity<?> updateInfo(@PathVariable("id") Long id, @RequestBody UserInfoForm userInfoForm) {
        User user = userService.findById(id).get();
        userService.updateUser(userInfoForm, user);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/userInfo/{id}")
    public ResponseEntity<?> userInfo(@PathVariable("id") Long id) {
        User user = userService.findById(id).get();
        UserInfoForm userInfo = userService.getUserInfo(user);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @GetMapping("/requestToMe/{uid}")
    public ResponseEntity<?> getListRequestToMe(@PathVariable("uid") Long uid){
        Iterable<User> list = userService.getListRequestToMe(uid);
        if (!list.iterator().hasNext()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
