package com.codegym.backendjavasocialnetwork.repository;

import com.codegym.backendjavasocialnetwork.entity.RelationalShip;
import com.codegym.backendjavasocialnetwork.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendShipRepository extends JpaRepository<RelationalShip, Long > {
    Optional<RelationalShip> findRelationshipByUser1_IdAndUser2_Id(Long user_1, Long target_user);

    @Query(value = "select * from relational_ship where user_1 = :id1 and target_user = :id2", nativeQuery = true)
    Optional<RelationalShip> findAllByUser1_IdAndUser2_Id(Long id1, Long id2);

}
