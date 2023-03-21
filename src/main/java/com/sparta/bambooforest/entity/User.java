package com.sparta.bambooforest.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "users")
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성
public class User extends TimeStamped{
    @Id // 데이터베이스 테이블의 기본 키(PK)와 객체의 필드를 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키를 자동 생성
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String email, String username, String password, UserRoleEnum role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
