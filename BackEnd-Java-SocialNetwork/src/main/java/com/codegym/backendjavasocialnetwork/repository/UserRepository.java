package com.codegym.backendjavasocialnetwork.repository;

import com.codegym.backendjavasocialnetwork.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    Boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);

    @Query(value = "select * from users where id in (SELECT DISTINCT F1.Person FROM ( SELECT F.user_1 Person FROM relational_ship F " +
            "WHERE F.target_user = :uid AND F.status_relational_ship = 'FRIENDS' UNION SELECT F.target_user Person FROM relational_ship F " +
            "WHERE F.user_1 = :uid AND F.status_relational_ship = 'FRIENDS') F1 INNER JOIN ( SELECT F.user_1 Person FROM relational_ship F " +
            "WHERE F.target_user = :id AND F.status_relational_ship = 'FRIENDS' UNION SELECT F.target_user Person FROM relational_ship F " +
            "WHERE F.user_1 = :id AND F.status_relational_ship = 'FRIENDS') F2 ON F2.Person = F1.Person)", nativeQuery = true)
    Iterable<User> getMutualFriendsList(Long uid, Long id);

    @Query(value = "select * from users where id in (select user_1 from relational_ship where target_user = :uid and status_relational_ship = 'PENDING')", nativeQuery = true)
    Iterable<User> getListRequestToMe(Long uid);

    @Query(value = "select * from users where id in (select target_user from relational_ship where user_1 = :uid and status_relational_ship = 'PENDING')", nativeQuery = true)
    Iterable<User> getListRequestFromMe(Long uid);

    @Query(value = "select * from users where id in (select target_user from relational_ship where user_1 = :uid and status_relational_ship = 'FRIENDS' union" +
            " all select user_1 from relational_ship where target_user = :uid and status_relational_ship = 'FRIENDS')", nativeQuery = true)
    Iterable<User> getListMyFriends(Long uid);
}
