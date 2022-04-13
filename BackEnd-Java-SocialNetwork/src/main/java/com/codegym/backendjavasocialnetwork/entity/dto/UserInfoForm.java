package com.codegym.backendjavasocialnetwork.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoForm {
    private String fullName;
    private String address;
    private String hobbies;
    private String avatarUrl;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
}
