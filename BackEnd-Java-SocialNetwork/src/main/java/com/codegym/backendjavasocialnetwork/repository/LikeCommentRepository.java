package com.codegym.backendjavasocialnetwork.repository;

import com.codegym.backendjavasocialnetwork.entity.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeCommentRepository extends JpaRepository<LikeComment, Long> {
    Optional<LikeComment> findByComment_IdAndUser_Id(Long comment_Id, Long user_Id);

    @Query(value = "SELECT count(comment_id) from like_comment where comment_id = :comment_id", nativeQuery = true)
    Integer countLikeCommentByComment_Id(Long comment_id);
}
