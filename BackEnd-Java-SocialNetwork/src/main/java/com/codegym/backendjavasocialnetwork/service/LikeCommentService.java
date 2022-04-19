package com.codegym.backendjavasocialnetwork.service;

import com.codegym.backendjavasocialnetwork.entity.LikeComment;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface LikeCommentService extends GenericService<LikeComment> {
    Optional<LikeComment> findByCommentIdAndUserId(Long comment_Id, Long user_Id);

    Integer countLikeCommentByComment_Id(Long comment_id);
}
