package com.codegym.backendjavasocialnetwork.controller;

import com.codegym.backendjavasocialnetwork.entity.LikePost;
import com.codegym.backendjavasocialnetwork.entity.Post;
import com.codegym.backendjavasocialnetwork.entity.User;
import com.codegym.backendjavasocialnetwork.service.LikePostService;
import com.codegym.backendjavasocialnetwork.service.PostService;
import com.codegym.backendjavasocialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/likePost")
public class LikePostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikePostService likePostService;

    @PostMapping("/{postId}/{userId}")
    public ResponseEntity<LikePost> checkLikePost(@PathVariable Long postId, @PathVariable Long userId) {
        Optional<LikePost> likePostOptional = likePostService.findByPostIdAndUserId(postId, userId);
        if (!likePostOptional.isPresent()) {
            Post post = postService.findById(postId).get();
            User user = userService.findById(userId).get();
            LikePost likePost = new LikePost(null, user, post);
            likePostService.save(likePost);
            return new ResponseEntity<>(likePost, HttpStatus.OK);
        } else {
            likePostService.remove(likePostOptional.get().getId());
            return new ResponseEntity<>(likePostOptional.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Long> countLikePost(@PathVariable Long postId) {
        Long countLike = likePostService.countLikeByPostId(postId);
        return new ResponseEntity<>(countLike, HttpStatus.OK);
    }

}