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
    @Query("select p from Post p where p.content like :content")
    Iterable<Post> findAllByContentContaining(String content);

    Iterable<Post> findAllByStatusOrderByIdDesc(Enum<Status> status);

    @Query(value = "select * from post where status = :status and user_id = :userId", nativeQuery = true)
    Iterable<Post> findAllByStatusAndUser_IdOrderByIdDesc(String status, Long userId);

    Iterable<Post> findAllByUser_IdOrderByIdDesc(Long userId);

    @Query("select p from Post p where p.status = 'PUBLIC' ")
    List<Post> getListPostByStatus();

    @Query(value = "select bang2.* from relational_ship as bang1 left join post as bang2 on bang1.target_user = bang2.user_id " +
            "where bang1.user_1 = :id and bang2.status = 'FRIENDS' and bang1.status_relational_ship = 'FRIENDS' union all" +
            " select * from post where status = 'PUBLIC' union all select * from post where (status = 'PRIVATE' or status = 'FRIENDS')" +
            " and user_id = :id union all select bang2.* from relational_ship as bang1 left join post as bang2 on bang1.user_1 = bang2.user_id " +
            "where bang1.target_user = :id and bang2.status = 'FRIENDS' and bang1.status_relational_ship = 'FRIENDS' order by id desc", nativeQuery = true)
    List<Post> getList(Long id);


}
