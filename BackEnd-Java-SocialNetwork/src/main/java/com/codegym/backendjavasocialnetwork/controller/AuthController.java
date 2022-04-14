package com.codegym.backendjavasocialnetwork.controller;

import com.codegym.backendjavasocialnetwork.entity.User;
import com.codegym.backendjavasocialnetwork.entity.dto.LoginForm;
import com.codegym.backendjavasocialnetwork.entity.dto.SignUpForm;
import com.codegym.backendjavasocialnetwork.jwt.JwtService;
import com.codegym.backendjavasocialnetwork.jwt.response.JwtResponse;
import com.codegym.backendjavasocialnetwork.repository.RoleRepository;
import com.codegym.backendjavasocialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm, Errors error) {
        if(userService.existsByUsername(signUpForm.getUsername())){
//            Mã 700 là tài khoản đã tồn tại
            return new ResponseEntity<>(700,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!signUpForm.getPassword().equals(signUpForm.getConfirmPassword())){
//            Mã 701 là sai xác nhận mật khẩu
            return new ResponseEntity<>(701,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (userService.existsByEmail(signUpForm.getEmail())){
//            Mã 702 là email đã tồn tại
            return new ResponseEntity<>(702, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        signUpForm.setRoleName("ROLE_USER");
        User user = new User(signUpForm.getUsername(),passwordEncoder.encode(signUpForm.getPassword()),
                signUpForm.getEmail(),signUpForm.getPhone(),signUpForm.getDateOfBirth(), roleRepository.findByName(signUpForm.getRoleName()));
        user.setAvatarUrl("https://trangtienich.com/wp-content/uploads/2019/12/anh-dai-dien-facebook-doc-11.jpg");
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {

        if (!passwordEncoder.matches(loginForm.getPassword(),
                userService.findByUsername(loginForm.getUsername()).get().getPassword())){
//            Mã 600 là lỗi sai mật khẩu
            return new ResponseEntity<>(600, HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.createToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUsername(loginForm.getUsername()).get();
        JwtResponse jwtResponse = new JwtResponse(currentUser.getId(),jwt,userDetails.getUsername(),currentUser.getEmail(), userDetails.getAuthorities());
        return ResponseEntity.ok(jwtResponse);
    }
}
