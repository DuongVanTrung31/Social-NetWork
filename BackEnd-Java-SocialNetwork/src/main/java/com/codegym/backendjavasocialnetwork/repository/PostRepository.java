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
    Iterable<Post> findAllByContentContaining(String content);

    Iterable<Post> findAllByStatusOrderByIdDesc(Enum<Status> status);

    Iterable<Post> findAllByUser_Id(Long userId);


    @Query("select p from Post p where p.status = 'PUBLIC' ")
    List<Post> getListPostByStatus();
}
