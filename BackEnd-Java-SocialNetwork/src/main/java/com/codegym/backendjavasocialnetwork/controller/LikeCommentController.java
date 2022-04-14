//package com.codegym.backendjavasocialnetwork.controller;
//
//import com.codegym.backendjavasocialnetwork.entity.Comment;
//import com.codegym.backendjavasocialnetwork.entity.LikeComment;
//import com.codegym.backendjavasocialnetwork.entity.User;
//import com.codegym.backendjavasocialnetwork.repository.LikeCommentRepository;
//import com.codegym.backendjavasocialnetwork.service.CommentService;
//import com.codegym.backendjavasocialnetwork.service.LikeCommentService;
//import com.codegym.backendjavasocialnetwork.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("api/like-comment")
//public class LikeCommentController {
//    @Autowired
//    private LikeCommentService likeCommentService;
//
//    @Autowired
//    private CommentService commentService;
//
//    @Autowired
//    private UserService userService;
//
//////    @PostMapping("/{comment_id}/{user_id}")
//////    public ResponseEntity<LikeComment> checkLike(@PathVariable("comment_id") long comment_id,
//////                                                 @PathVariable("user_id") long user_id) {
//////        Optional<LikeComment> likeCommentOptional = likeCommentService.findByCommentIdAndUserId(comment_id, user_id);
//////        if(!likeCommentOptional.isPresent()) {
//////            User user = userService.findById(user_id).get();
//////            Comment comment = commentService.findById(comment_id).get();
//////            LikeComment likeComment = new LikeComment(comment, user);
//////            likeCommentService.save(likeComment);
////
////        }
////    }
////}
