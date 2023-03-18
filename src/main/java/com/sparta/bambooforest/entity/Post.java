package com.sparta.bambooforest.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
public class Post extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private Long viewCount;
    @Column(nullable = false)
    private Long likeCount;
    @Column(nullable = false)
    private Long commentCount;

}
