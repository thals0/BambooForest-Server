package com.sparta.bambooforest.controller;

import com.sparta.bambooforest.dto.PostRequestDto;
import com.sparta.bambooforest.dto.PostResponseDto;
import com.sparta.bambooforest.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    public PostResponseDto savePost(@RequestBody PostRequestDto postRequestDto, HttpServletRequest httpServletRequest){
        return  postService.savePost(postRequestDto,httpServletRequest);
    }

}
