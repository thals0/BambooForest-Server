package com.sparta.bambooforest.entity;

import com.sparta.bambooforest.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Post extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 생성되게함. 전략이 identity(mysql),sequence(oracle)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
//    @Column(nullable = false)
//    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();


    public Post(PostRequestDto postRequestDto, User user) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
//        this.type = postRequestDto.getType();
        this.user = user;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
