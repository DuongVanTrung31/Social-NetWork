package com.codegym.backendjavasocialnetwork.controller;
import com.codegym.backendjavasocialnetwork.entity.Comment;
import com.codegym.backendjavasocialnetwork.service.CommentService;
import com.codegym.backendjavasocialnetwork.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<Comment>> findAllByPost(@PathVariable("id") Long id) {
        Iterable<Comment> comments = commentService.findAllCommentInPostById(id);
        if(!comments.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
    }

    @PostMapping("/{pid}")
    public ResponseEntity<Comment> saveComment(@PathVariable("pid") Long id, @RequestBody Comment comment) {
        Comment comment1 = new Comment();
        if(comment.getId() != null) {
            comment1 = commentService.findById(comment.getId()).get();
            comment1.setContent(comment.getContent());
        } else {
            comment1.setContent(comment.getContent());
            comment1.setUser(comment.getUser());
            comment1.setPost(postService.findById(id).get());
        }
        return new ResponseEntity<>(commentService.saveComment(comment1), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> editComment(@PathVariable("id") Long id, @RequestBody Comment comment) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if(!commentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            comment.setId(commentOptional.get().getId());
            comment = commentService.saveComment(comment);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable("id") Long id) {
        Optional<Comment> comment1 = commentService.findById(id);
        if(!comment1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            commentService.deleteComment(id);
            return new ResponseEntity<>(comment1.get(), HttpStatus.OK);
        }
    }
}
