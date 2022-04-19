package com.codegym.backendjavasocialnetwork.service;

import com.codegym.backendjavasocialnetwork.entity.Comment;

import java.util.Optional;

public interface CommentService {

    Optional<Comment> findById(Long id);

    Comment saveComment(Comment comment);

    void deleteComment(Long id);

    Iterable<Comment> findAllCommentInPostById(Long id);

}
