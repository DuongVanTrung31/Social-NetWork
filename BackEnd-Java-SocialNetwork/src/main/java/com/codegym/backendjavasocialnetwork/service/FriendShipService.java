package com.codegym.backendjavasocialnetwork.service;

import com.codegym.backendjavasocialnetwork.entity.RelationalShip;
import java.util.Optional;

public interface FriendShipService extends GenericService<RelationalShip> {
    Optional<RelationalShip> findRelationshipByUser1AndUser2 (Long userId , Long targetUserId);
}
