package com.codegym.backendjavasocialnetwork.service;

import com.codegym.backendjavasocialnetwork.entity.LikePost;

import java.util.Optional;

public interface LikePostService extends GenericService<LikePost> {
    Optional<LikePost> findByPostIdAndUserId(Long postId, Long userId);

    Long countLikeByPostId(Long postId);
}
