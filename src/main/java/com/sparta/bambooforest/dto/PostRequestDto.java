package com.sparta.bambooforest.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostRequestDto {
    private String title;
    private String content;
    private String type;

    public PostRequestDto() {}

}
