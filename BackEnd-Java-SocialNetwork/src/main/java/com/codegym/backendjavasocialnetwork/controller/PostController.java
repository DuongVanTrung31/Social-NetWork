package com.codegym.backendjavasocialnetwork.controller;

import com.codegym.backendjavasocialnetwork.entity.Post;
import com.codegym.backendjavasocialnetwork.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("api/post")
public class PostController {
    @Autowired
    private IPostService postService;

    //Lay bai viet
    @GetMapping
    public ResponseEntity<Iterable<Post>> getAll() {
        Iterable<Post> posts = postService.findAll();
        if (!posts.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    //Tao bai viet
    @PostMapping("/{userId}")
    public ResponseEntity<Post> savePost(@PathVariable("userId") Long userId, @RequestPart("post") Post post) {
        post.setCreatedDate(LocalDateTime.now());
        Post createPost = postService.save(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Sua
    @PutMapping("{/id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @RequestPart("postUpdate") Post postUpdate) {
        Optional<Post> post = postService.findById(id);
        if (!post.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postUpdate.setId(post.get().getId());
        postUpdate.setUpdatedDate(post.get().getUpdatedDate());
        postUpdate.setStatus(post.get().getStatus());
        postUpdate = postService.save(postUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //xoa bai viet
    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        Optional<Post> post = postService.findById(id);
        if (!post.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}