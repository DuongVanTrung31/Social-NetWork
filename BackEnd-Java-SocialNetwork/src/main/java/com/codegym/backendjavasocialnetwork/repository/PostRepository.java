package com.codegym.backendjavasocialnetwork.repository;

import com.codegym.backendjavasocialnetwork.entity.Post;
import com.codegym.backendjavasocialnetwork.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p where p.content like %?1%")
    Iterable<Post> findAllByContentContaining(String content);

    //    @Query("select p from Post p left join p.user u where u =?1")
//    @Query("select p from Post p where u =?1")
    Iterable<Post> findAllByUser(Long userId);

    Iterable<Post> findAllByStatus(String status);

    @Query("select p from Post p where p.status in :statusList")
    List<Post> getListPostByStatus(@Param("statusList") List<Status> statusInput);
}
