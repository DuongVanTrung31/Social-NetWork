package com.codegym.backendjavasocialnetwork.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LikeComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
}
