package com.sparta.bambooforest.dto;

import java.time.LocalDateTime;

@Getter
public class PostRequestDto {
    private Long id;
    private String title;
    private String content;
    private String usersName;
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;

    public PostRequestDto() {}

}