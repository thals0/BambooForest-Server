package com.sparta.bambooforest.dto;


import com.sparta.bambooforest.entity.Post;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
//    private String type;
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;

    //미구현 파트 (추가 예정)
//    private Long viewCount;
//    private Long likeCount;
//    private Long commentCount;
    private List<CommentResponseDto> commentList; // = new ArrayList<>();

    public PostResponseDto() {}

    public PostResponseDto(Post post) {
        this.id= post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
//        this.type = post.getType();
        this.modifiedAt = post.getModifiedAt();
        this.createdAt = post.getCreatedAt();
    }

    public PostResponseDto(Post post, List<CommentResponseDto> commentResponseDtoList) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
//        this.type = post.getType();
        this.modifiedAt = post.getModifiedAt();
        this.createdAt = post.getCreatedAt();
        this.commentList = commentResponseDtoList;
    }


}
