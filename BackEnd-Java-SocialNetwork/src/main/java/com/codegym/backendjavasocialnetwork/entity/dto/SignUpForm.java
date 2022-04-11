package com.codegym.backendjavasocialnetwork.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpForm {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
}
