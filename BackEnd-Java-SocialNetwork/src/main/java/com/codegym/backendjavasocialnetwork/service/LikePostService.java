package com.codegym.backendjavasocialnetwork.service;

import com.codegym.backendjavasocialnetwork.entity.LikePost;

import java.util.Optional;

public interface LikePostService extends GenericService<LikePost> {
    Optional<LikePost> findByPostIdAndUserId(Long PostId, Long UserId);
}
