package com.codegym.backendjavasocialnetwork.service.impl;

import com.codegym.backendjavasocialnetwork.entity.Post;
import com.codegym.backendjavasocialnetwork.entity.dto.PostResponseDTO;
import com.codegym.backendjavasocialnetwork.entity.dto.PostStatusRequest;
import com.codegym.backendjavasocialnetwork.entity.enums.Status;
import com.codegym.backendjavasocialnetwork.repository.PostRepository;
import com.codegym.backendjavasocialnetwork.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Iterable<Post> findAllByContent(String content) {
        return postRepository.findAllByContentContaining(content);
    }


    @Override
    public List<Post> getListPostByStatus() {
        return postRepository.getListPostByStatus();
    }

    @Override
    public Iterable<Post> findAllByUser_Id(Long userId) {
        return postRepository.findAllByUser_Id(userId);
    }

    @Override
    public Iterable<Post> findAllByStatusOrderByIdDesc(Enum<Status> status) {
        return postRepository.findAllByStatusOrderByIdDesc(status);
    }

    @Override
    public Iterable<Post> findAllByStatusAndUser_IdOrderByIdDesc(String status, Long userId) {
        return postRepository.findAllByStatusAndUser_IdOrderByIdDesc(status, userId);
    }


}
