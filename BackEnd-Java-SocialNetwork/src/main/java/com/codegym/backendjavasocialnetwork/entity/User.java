package com.codegym.backendjavasocialnetwork.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Table(name = "users")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String fullName;
    private String avatarUrl;
    private String hobbies;

    @ManyToOne
    private Role role;
}
