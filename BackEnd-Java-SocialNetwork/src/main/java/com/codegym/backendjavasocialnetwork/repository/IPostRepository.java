package com.codegym.backendjavasocialnetwork.repository;

import com.codegym.backendjavasocialnetwork.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {
}
