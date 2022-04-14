package com.codegym.backendjavasocialnetwork.service.impl;

import com.codegym.backendjavasocialnetwork.entity.LikePost;
import com.codegym.backendjavasocialnetwork.repository.LikePostRepository;
import com.codegym.backendjavasocialnetwork.service.LikePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikePostServiceImpl implements LikePostService {
    @Autowired
    private LikePostRepository likePostRepository;

    @Override
    public Iterable<LikePost> findAll() {
        return likePostRepository.findAll();
    }

    @Override
    public Optional<LikePost> findById(Long id) {
        return likePostRepository.findById(id);
    }

    @Override
    public void save(LikePost likePost) {
        likePostRepository.save(likePost);
    }

    @Override
    public void remove(Long id) {
        likePostRepository.deleteById(id);
    }

    @Override
    public Optional<LikePost> findByPostIdAndUserId(Long postId, Long userId) {
        return likePostRepository.findByPostIdAndUserId(postId, userId);
    }

    @Override
    public Long countLikeByPostId(Long postId) {
        return likePostRepository.countLikeByPostId(postId);
    }
}
