package com.codegym.backendjavasocialnetwork.controller;

import com.codegym.backendjavasocialnetwork.entity.Comment;
import com.codegym.backendjavasocialnetwork.entity.LikeComment;
import com.codegym.backendjavasocialnetwork.entity.User;
import com.codegym.backendjavasocialnetwork.service.CommentService;
import com.codegym.backendjavasocialnetwork.service.LikeCommentService;
import com.codegym.backendjavasocialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/like-comment")
public class LikeCommentController {
    @Autowired
    private LikeCommentService likeCommentService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/{comment_id}/{user_id}")
    public ResponseEntity<LikeComment> checkLike(@PathVariable("comment_id") long comment_id,
                                                 @PathVariable("user_id") long user_id) {
        Optional<LikeComment> likeCommentOptional = likeCommentService.findByCommentIdAndUserId(comment_id, user_id);
        if(!likeCommentOptional.isPresent()) {
            User user = userService.findById(user_id).get();
            Comment comment = commentService.findById(comment_id).get();
            LikeComment likeComment = new LikeComment(null ,user, comment);
            likeCommentService.save(likeComment);
            return new ResponseEntity<>(likeComment, HttpStatus.OK);
        } else {
            likeCommentService.remove(likeCommentOptional.get().getId());
            return new ResponseEntity<>(likeCommentOptional.get(), HttpStatus.OK);
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<LikeComment> countLikeCommentByCommentId(@PathVariable("id") long id) {
//        Long countLikeCommentByCommentId = likeCommentService.countLikeCommentByComment_Id(id);
//        return new ResponseEntity<>(countLikeCommentByCommentId, HttpStatus.OK);
//    }
}
