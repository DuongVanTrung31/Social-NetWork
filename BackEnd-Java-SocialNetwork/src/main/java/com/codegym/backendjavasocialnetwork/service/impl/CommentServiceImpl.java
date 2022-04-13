package com.codegym.backendjavasocialnetwork.service.impl;

import com.codegym.backendjavasocialnetwork.entity.Comment;
import com.codegym.backendjavasocialnetwork.repository.CommentRepository;
import com.codegym.backendjavasocialnetwork.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment saveComment(Comment comment) {
        return (Comment) commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.delete(id);
    }

    @Override
    public Iterable<Comment> findAllCommentInPostById(Long id) {
        return commentRepository.findAllCommentInPostById(id);
    }

}
