package com.codegym.backendjavasocialnetwork.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LikeComment> likeCommentList;
}
