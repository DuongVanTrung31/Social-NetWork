package com.codegym.backendjavasocialnetwork.repository;

import com.codegym.backendjavasocialnetwork.entity.RelationalShip;
import org.aspectj.asm.internal.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendShipRepository extends JpaRepository<RelationalShip, Long > {
    Optional<RelationalShip> findRelationshipByUser1_IdAndUser2_Id(Long user_1, Long target_user);
}
