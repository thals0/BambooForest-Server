package com.sparta.bambooforest.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String usersName;
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;

    public PostResponseDto() {}
}
