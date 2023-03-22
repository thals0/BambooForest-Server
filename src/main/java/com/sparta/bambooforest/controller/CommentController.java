package com.sparta.bambooforest.controller;

import com.sparta.bambooforest.dto.CommentRequestDto;

import com.sparta.bambooforest.dto.CommentResponseDto;
import com.sparta.bambooforest.entity.User;
import com.sparta.bambooforest.security.UserDetailsImpl;
import com.sparta.bambooforest.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{id}")
    public CommentResponseDto createComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(id,commentRequestDto,userDetails.getUser());
    }

    @PutMapping("/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.update(id, commentRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.delete(id,userDetails.getUser());
    }

}
