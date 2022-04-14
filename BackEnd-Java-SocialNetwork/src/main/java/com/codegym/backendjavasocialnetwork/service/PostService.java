package com.codegym.backendjavasocialnetwork.service;

import com.codegym.backendjavasocialnetwork.entity.Post;
import com.codegym.backendjavasocialnetwork.entity.dto.PostStatusRequest;

import java.util.List;

public interface PostService extends GenericService<Post> {
    Iterable<Post> findAllByContent(String name);
    Iterable<Post> findAllByStatus(String status);

    List<Post> getListPostByStatus(PostStatusRequest request);
}
