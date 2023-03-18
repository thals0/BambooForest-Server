package com.sparta.bambooforest.dto;

import com.sparta.bambooforest.entity.User;
import lombok.Getter;

@Getter
public class SignupResponseDto {
    private final String email;
    private final String username;
    private final String password;

    public SignupResponseDto(User user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
