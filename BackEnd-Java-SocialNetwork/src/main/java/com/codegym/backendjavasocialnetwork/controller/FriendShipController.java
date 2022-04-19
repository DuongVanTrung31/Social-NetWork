package com.codegym.backendjavasocialnetwork.controller;

import com.codegym.backendjavasocialnetwork.entity.Post;
import com.codegym.backendjavasocialnetwork.entity.RelationalShip;
import com.codegym.backendjavasocialnetwork.entity.User;
import com.codegym.backendjavasocialnetwork.entity.enums.Status;
import com.codegym.backendjavasocialnetwork.service.FriendShipService;
import com.codegym.backendjavasocialnetwork.service.PostService;
import com.codegym.backendjavasocialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.codegym.backendjavasocialnetwork.entity.enums.StatusRelationalShip.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/relationShip")
public class FriendShipController {

    @Autowired
    private FriendShipService friendShipService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @PostMapping("/{userId}/{targetUserId}")
    public ResponseEntity<?> addFriend(@PathVariable("userId") Long userId, @PathVariable("targetUserId") Long targetUserId) {
        User user = userService.findById(userId).get();
        User targetUser = userService.findById(targetUserId).get();
        if (!(friendShipService.findRelationshipByUser1AndUser2(userId, targetUserId).isPresent() ||
                friendShipService.findRelationshipByUser1AndUser2(targetUserId, userId).isPresent())) {
            RelationalShip relationShip = new RelationalShip(null, user, targetUser, PENDING);
            friendShipService.save(relationShip);
            return new ResponseEntity<>(PENDING, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{userId}/{targetUserId}")
    public ResponseEntity<?> acceptFriend(@PathVariable("userId") Long userId, @PathVariable("targetUserId") Long targetUserId) {
        User user = userService.findById(userId).get();
        User targetUser = userService.findById(targetUserId).get();
        if (friendShipService.findRelationshipByUser1AndUser2(userId, targetUserId).isPresent() ||
                friendShipService.findRelationshipByUser1AndUser2(targetUserId, userId).isPresent()) {
            RelationalShip relationShip = friendShipService.findRelationshipByUser1AndUser2(targetUser.getId(), user.getId()).get();
            relationShip.setStatusRelationalShip(FRIENDS);
            if (relationShip != null) {
                friendShipService.save(relationShip);
            }
            return new ResponseEntity<>(FRIENDS, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{userId}/{targetUserId}")
    public ResponseEntity<?> unFriend(@PathVariable("userId") Long userId, @PathVariable("targetUserId") Long targetUserId) {
        User user = userService.findById(userId).get();
        User targetUser = userService.findById(targetUserId).get();
        Optional<RelationalShip> optionalFriendship = friendShipService.findRelationshipByUser1AndUser2(user.getId(), targetUser.getId());
        optionalFriendship.ifPresent(relationalShip -> friendShipService.remove(relationalShip.getId()));
        Optional<RelationalShip> optionalFriendship1 = friendShipService.findRelationshipByUser1AndUser2(targetUser.getId(), user.getId());
        optionalFriendship1.ifPresent(relationalShip -> friendShipService.remove(relationalShip.getId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/block/{userId}/{targetUserId}")
    public ResponseEntity<?> blockFriend(@PathVariable("userId") Long userId, @PathVariable("targetUserId") Long targetUserId) {
        User user = userService.findById(userId).get();
        User targetUser = userService.findById(targetUserId).get();
        Optional<RelationalShip> optionalFriendship = friendShipService.findRelationshipByUser1AndUser2(user.getId(), targetUser.getId());
        if (optionalFriendship.isPresent()) {
            optionalFriendship.get().setStatusRelationalShip(BLOCKED);
            friendShipService.save(optionalFriendship.get());
        }
        Optional<RelationalShip> optionalFriendship1 = friendShipService.findRelationshipByUser1AndUser2(targetUser.getId(), user.getId());
        if (optionalFriendship1.isPresent()) {
            optionalFriendship1.get().setStatusRelationalShip(BLOCKED);
            friendShipService.save(optionalFriendship1.get());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/MutualFriends/{uid}/{id}")
    public ResponseEntity<?> getMutualFriends(@PathVariable("uid") Long uid, @PathVariable("id") Long id) {
        if (userService.getMutualFriendsList(uid, id) != null) {
            return new ResponseEntity<>(userService.getMutualFriendsList(uid, id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/checkRelationship/{uid}/{id}")
    public ResponseEntity<?> checkRelationship(@PathVariable("uid") Long uid, @PathVariable("id") Long id) {
        Optional<RelationalShip> relationalShip = friendShipService.findRelationshipByUser1AndUser2(uid, id);
        Optional<RelationalShip> relationalShip1 = friendShipService.findRelationshipByUser1AndUser2(id, uid);
        RelationalShip relational = null;
        if (relationalShip.isPresent() || relationalShip1.isPresent()) {
            relational = relationalShip.orElseGet(relationalShip1::get);
        }
        if (relational == null) {
            return new ResponseEntity<>(NOT_FRIEND, HttpStatus.OK);
        } else if (relational.getStatusRelationalShip().equals(FRIENDS)) {
            return new ResponseEntity<>(FRIENDS, HttpStatus.OK);
        } else if (relational.getStatusRelationalShip().equals(PENDING)) {
            return new ResponseEntity<>(PENDING, HttpStatus.OK);
        } else return new ResponseEntity<>(BLOCKED, HttpStatus.OK);
    }

    @GetMapping("/listStatus/{uid}/{id}")
    public ResponseEntity<?> getListStatus(@PathVariable("uid") Long uid, @PathVariable("id") Long id) {
        Optional<RelationalShip> relationalShip = friendShipService.findRelationshipByUser1AndUser2(uid, id);
        Optional<RelationalShip> relationalShip1 = friendShipService.findRelationshipByUser1AndUser2(id, uid);
        RelationalShip relational = null;
        List<Post> postPublics = (List<Post>) postService.findAllByStatusOrderByIdDesc(Status.PUBLIC);
        List<Post> postList = (List<Post>) postService.findAllByStatusOrderByIdDesc(Status.FRIENDS);
        ;
        postList.addAll(postPublics);
        if (relationalShip.isPresent() || relationalShip1.isPresent()) {
            relational = relationalShip.orElseGet(relationalShip1::get);
        }
        if (relational == null) {
            return new ResponseEntity<>(postPublics, HttpStatus.OK);
        } else if (relational.getStatusRelationalShip().equals(FRIENDS)) {
            return new ResponseEntity<>(postList, HttpStatus.OK);
        } else if (relational.getStatusRelationalShip().equals(PENDING)){
            return new ResponseEntity<>(postPublics, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
