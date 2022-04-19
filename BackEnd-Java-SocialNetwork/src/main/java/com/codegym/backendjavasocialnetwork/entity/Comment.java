package com.codegym.backendjavasocialnetwork.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @JsonIgnore
    @ManyToOne
    private Post post;

    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "comment",cascade = CascadeType.ALL)
    private List<LikeComment> likeCommentList;

    private Long parentId;
}
