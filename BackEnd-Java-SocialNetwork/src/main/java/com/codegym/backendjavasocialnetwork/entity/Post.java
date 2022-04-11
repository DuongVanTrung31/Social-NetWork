package com.codegym.backendjavasocialnetwork.entity;

import com.codegym.backendjavasocialnetwork.entity.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String image;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> commentList;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<LikePost> likePostList;
}
