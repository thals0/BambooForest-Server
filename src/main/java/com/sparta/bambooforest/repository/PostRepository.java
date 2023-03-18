package com.sparta.bambooforest.repository;

import com.sparta.bambooforest.entity.Post;
import com.sparta.bambooforest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long > {
    List<Post> findAllByOrderByModifiedAtDesc();

    Optional<Post> findByPostIdAndUser(Long postId, User user);
}

