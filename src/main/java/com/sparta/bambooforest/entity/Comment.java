package com.sparta.bambooforest.entity;

import com.sparta.bambooforest.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment(CommentRequestDto commentRequestDto, User user, Post post) {
        this.comment = commentRequestDto.getComment();
        this.user = user;
        this.post = post;
    }


    public void update(CommentRequestDto commentRequestDto){
        this.comment=commentRequestDto.getComment();
    }
}
