package com.codegym.backendjavasocialnetwork.jwt.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@AllArgsConstructor
@Data
public class JwtResponse {
    private Long id;
    private String token;
    private final String type = "Bearer";
    private String username;
    private String email;
    private Collection<? extends GrantedAuthority> role;
}