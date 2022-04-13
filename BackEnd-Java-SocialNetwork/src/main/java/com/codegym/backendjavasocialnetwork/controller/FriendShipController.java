package com.codegym.backendjavasocialnetwork.controller;

import com.codegym.backendjavasocialnetwork.entity.RelationalShip;
import com.codegym.backendjavasocialnetwork.entity.User;
import com.codegym.backendjavasocialnetwork.service.FriendShipService;
import com.codegym.backendjavasocialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<?> unFriend(@PathVariable("userId") Long userId, @PathVariable("targetUserId") Long targetUserId){
        User user = userService.findById(userId).get();
        User targetUser = userService.findById(targetUserId).get();
        Optional<RelationalShip> optionalFriendship = friendShipService.findRelationshipByUser1AndUser2(user.getId(), targetUser.getId());
        optionalFriendship.ifPresent(relationalShip -> friendShipService.remove(relationalShip.getId()));
        Optional<RelationalShip> optionalFriendship1 = friendShipService.findRelationshipByUser1AndUser2(targetUser.getId(), user.getId());
        optionalFriendship1.ifPresent(relationalShip -> friendShipService.remove(relationalShip.getId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/block/{userId}/{targetUserId}")
    public ResponseEntity<?> blockFriend(@PathVariable("userId") Long userId, @PathVariable("targetUserId") Long targetUserId){
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
            friendShipService.save(optionalFriendship.get());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
