package com.sparta.bambooforest.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private final String email;
    private final String password;

    public LoginResponseDto(LoginRequestDto loginRequestDto) {
        this.email = loginRequestDto.getEmail();
        this.password = loginRequestDto.getPassword();
    }
}
