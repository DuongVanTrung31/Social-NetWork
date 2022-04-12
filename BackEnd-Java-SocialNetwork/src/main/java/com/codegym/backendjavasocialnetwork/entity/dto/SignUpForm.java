package com.codegym.backendjavasocialnetwork.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpForm {
    @NotEmpty
    private String username;

    @Size(min = 6, max = 32)
    private String password;
    private String confirmPassword;

    @Pattern(regexp = "^[a-zA-Z][a-z0-9]{0,9}@[a-z]+\\.(com|vn)+$")
    private String email;

    @Pattern(regexp = "^0([9873])[0-9]{8}$")
    private String phone;
    private LocalDate dateOfBirth;
    private String roleName;
}
