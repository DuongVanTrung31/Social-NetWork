package com.codegym.backendjavasocialnetwork.controller;

import com.codegym.backendjavasocialnetwork.entity.Post;
import com.codegym.backendjavasocialnetwork.entity.RelationalShip;
import com.codegym.backendjavasocialnetwork.entity.User;
import com.codegym.backendjavasocialnetwork.entity.enums.Status;
import com.codegym.backendjavasocialnetwork.entity.enums.StatusRelationalShip;
import com.codegym.backendjavasocialnetwork.service.FriendShipService;
import com.codegym.backendjavasocialnetwork.service.PostService;
import com.codegym.backendjavasocialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@Controller
@CrossOrigin("*")
@RequestMapping("api/post")
public class PostController {

    @Autowired
    private FriendShipService friendShipService;

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Iterable<Post>> getAll() {
        Iterable<Post> posts = postService.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Post> savePost(@PathVariable("userId") Long userId,
                                         @RequestBody Post post) {
        Optional<User> user = userService.findById(userId);
        if (!user.isPresent()) {
            throw new RuntimeException("User doesn't exist");
        }
        post.setCreatedDate(LocalDateTime.now());
        post.setUser(user.get());
        postService.save(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @RequestBody Post postUpdate) {
        Optional<Post> post = postService.findById(id);
        if (!post.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postUpdate.setId(post.get().getId());
        postUpdate.setUpdatedDate(post.get().getUpdatedDate());
        postUpdate.setStatus(post.get().getStatus());
        postService.save(postUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable("id") Long id) {
        Optional<Post> post = postService.findById(id);
        if (!post.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/searchPost")
    public ResponseEntity<Iterable<Post>> getPostByContent(@RequestBody String search) {
        Iterable<Post> posts = postService.findAllByContent(search);
        if (!posts.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<Iterable<Post>> getListPostByStatus() {

        Iterable<Post> postList = postService.findAllByStatusOrderByIdDesc(Status.PUBLIC);
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("/{uid}")
    public ResponseEntity<Iterable<Post>> getAllByUser(@PathVariable("uid") Long id) {
        Iterable<Post> posts = postService.findAllByUser_Id(id);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("newFeeds/{uid}")
    public ResponseEntity<?> getNewFeed(@PathVariable("uid") Long uid){
        List<Post> privatePosts = (List<Post>) postService.findAllByStatusAndUser_IdOrderByIdDesc("PRIVATE", uid);
        List<Post> postList = (List<Post>) postService.findAllByStatusOrderByIdDesc(Status.PUBLIC);
        if (privatePosts != null){
            postList.addAll(privatePosts);
        }
        User user = userService.findById(uid).get();
        List<Post> posts = (List<Post>) postService.findAllByStatusOrderByIdDesc(Status.FRIENDS);
        for (Post p : posts){
            Optional<RelationalShip> OptionalRelationalShip = friendShipService.findAllByUser1_IdAndUser2_Id(user.getId(), p.getUser().getId());
            RelationalShip relationalShip = new RelationalShip();
            if (OptionalRelationalShip.isPresent()){
                 relationalShip = OptionalRelationalShip.get();
            } else{
                Optional<RelationalShip> OptionalRelationalShip1 = friendShipService.findAllByUser1_IdAndUser2_Id(p.getUser().getId(), user.getId());
                if (OptionalRelationalShip1.isPresent()){
                    relationalShip = OptionalRelationalShip1.get();
                }
            }
            if(relationalShip.getId() != null){
                if(relationalShip.getStatusRelationalShip().equals(StatusRelationalShip.FRIENDS)){
                    postList.add(p);
                    return new ResponseEntity<>(postList, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }
}