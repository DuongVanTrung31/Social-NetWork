package com.codegym.backendjavasocialnetwork.service.impl;

import com.codegym.backendjavasocialnetwork.entity.Post;
import com.codegym.backendjavasocialnetwork.entity.dto.PostResponseDTO;
import com.codegym.backendjavasocialnetwork.entity.dto.PostStatusRequest;
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
    public Iterable<Post> findAllByStatus(String status) {
        return postRepository.findAllByStatus(status);
    }

    @Override
    public List<Post> getListPostByStatus(PostStatusRequest request) {
        return postRepository.getListPostByStatus(request.getStatusInput());
    }


}
