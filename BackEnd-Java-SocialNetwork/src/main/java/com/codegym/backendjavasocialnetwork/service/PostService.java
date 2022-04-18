package com.codegym.backendjavasocialnetwork.service;

import com.codegym.backendjavasocialnetwork.entity.Post;
import com.codegym.backendjavasocialnetwork.entity.enums.Status;


import java.util.List;

public interface PostService extends GenericService<Post> {
    Iterable<Post> findAllByContentContaining(String content);

    List<Post> getListPostByStatus();

    Iterable<Post> findAllByUser_Id(Long userId);

    Iterable<Post> findAllByStatusOrderByIdDesc(Enum<Status> status);
}
