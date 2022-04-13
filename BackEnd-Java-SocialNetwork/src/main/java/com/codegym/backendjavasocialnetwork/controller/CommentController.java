package com.codegym.backendjavasocialnetwork.controller;
import com.codegym.backendjavasocialnetwork.entity.Comment;
import com.codegym.backendjavasocialnetwork.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<Comment>> findAllByPost(@PathVariable("id") Long id) {
        Iterable<Comment> comments = commentService.findAllCommentInPostById(id);

        if(!comments.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        return new ResponseEntity<>(commentService.saveComment(comment), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> editComment(@PathVariable("id") long id, @RequestBody Comment comment) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if(!commentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            comment.setId(commentOptional.get().getId());
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable("id") long id, @RequestBody Comment comment) {
        Optional<Comment> comment1 = commentService.findById(id);
        if(!comment1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            commentService.deleteComment(id);
            return new ResponseEntity<>(comment1.get(), HttpStatus.OK);
        }
    }
}
