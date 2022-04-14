package com.codegym.backendjavasocialnetwork.service;

import com.codegym.backendjavasocialnetwork.entity.LikeComment;



import java.util.Optional;

public interface LikeCommentService extends GenericService<LikeComment> {
    Optional<LikeComment> findByCommentIdAndUserId(Long comment_Id, Long user_Id);

    Long countLikeCommentByComment_Id(Long post_id);
}
