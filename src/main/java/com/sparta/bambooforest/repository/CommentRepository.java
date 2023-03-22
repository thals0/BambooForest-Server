package com.sparta.bambooforest.repository;

import com.sparta.bambooforest.entity.Comment;
import com.sparta.bambooforest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Optional<Object> findByIdAndUser(Long id, User user);
}
