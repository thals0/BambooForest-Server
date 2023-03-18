package com.sparta.bambooforest.repository;

import com.sparta.bambooforest.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long > {
}

