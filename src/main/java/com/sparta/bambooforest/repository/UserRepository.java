package com.sparta.bambooforest.repository;

import com.sparta.bambooforest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
