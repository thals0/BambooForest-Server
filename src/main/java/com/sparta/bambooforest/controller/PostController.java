package com.sparta.bambooforest.controller;

import com.sparta.bambooforest.dto.PostRequestDto;
import com.sparta.bambooforest.dto.PostResponseDto;
import com.sparta.bambooforest.security.UserDetailsImpl;
import com.sparta.bambooforest.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.createPost(boardRequestDto, userDetails.getUser());
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return (PostResponseDto) postService.getPost(id,userDetails.getUser());
    }

    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.updatePost(id, postRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.deletePost(id, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body("게시글 삭제 성공");
    }



}
