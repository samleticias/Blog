package com.example.blog.domain.repositories;

import com.example.blog.domain.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPost_PostIdAndUser_UserId(Long postId, Long userId);

}
