package com.codegym.backendjavasocialnetwork.service;

import com.codegym.backendjavasocialnetwork.entity.Post;


import java.util.List;

public interface PostService extends GenericService<Post> {
    Iterable<Post> findAllByContent(String name);

    List<Post> getListPostByStatus();

    Iterable<Post> findAllByUser_Id(Long userId);
}
