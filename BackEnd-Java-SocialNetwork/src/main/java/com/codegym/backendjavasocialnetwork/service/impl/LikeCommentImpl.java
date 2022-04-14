package com.codegym.backendjavasocialnetwork.service.impl;

import com.codegym.backendjavasocialnetwork.entity.LikeComment;
import com.codegym.backendjavasocialnetwork.repository.LikeCommentRepository;
import com.codegym.backendjavasocialnetwork.service.LikeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class LikeCommentImpl implements LikeCommentService {
    @Autowired
    private LikeCommentRepository likeCommentRepository;

    @Override
    public Iterable<LikeComment> findAll() {
        return likeCommentRepository.findAll();
    }

    @Override
    public Optional<LikeComment> findById(Long id) {
        return likeCommentRepository.findById(id);
    }

    @Override
    public void save(LikeComment likeComment) {
        likeCommentRepository.save(likeComment);
    }

    @Override
    public void remove(Long id) {
        likeCommentRepository.deleteById(id);
    }

    @Override
    public Optional<LikeComment> findByCommentIdAndUserId(Long comment_Id, Long user_Id) {
        return likeCommentRepository.findByCommentIdAndUserId(comment_Id, user_Id);
    }

    @Override
    public Long countLikeCommentByCommentId(Long id) {
        return likeCommentRepository.countLikeCommentByCommentId(id);
    }

    @Override
    public void deleteAllByCommentId(Long id) {
        likeCommentRepository.deleteAllByCommentId(id);
    }
}
