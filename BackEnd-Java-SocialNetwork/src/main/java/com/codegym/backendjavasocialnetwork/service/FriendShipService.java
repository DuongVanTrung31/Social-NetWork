package com.codegym.backendjavasocialnetwork.service;

import com.codegym.backendjavasocialnetwork.entity.RelationalShip;
import com.codegym.backendjavasocialnetwork.entity.User;

import java.util.List;
import java.util.Optional;

public interface FriendShipService extends GenericService<RelationalShip> {
    Optional<RelationalShip> findRelationshipByUser1AndUser2 (Long userId , Long targetUserId);
    Optional<RelationalShip> findAllByUser1_IdAndUser2_Id(Long user_1, Long target_user);
}
