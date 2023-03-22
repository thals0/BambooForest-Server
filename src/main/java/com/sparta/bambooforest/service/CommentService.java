package com.sparta.bambooforest.service;

import com.sparta.bambooforest.dto.CommentRequestDto;
import com.sparta.bambooforest.dto.CommentResponseDto;
import com.sparta.bambooforest.entity.Comment;
import com.sparta.bambooforest.entity.Post;
import com.sparta.bambooforest.entity.User;
import com.sparta.bambooforest.entity.UserRoleEnum;
import com.sparta.bambooforest.exception.CustomException;
import com.sparta.bambooforest.exception.ErrorCode;
import com.sparta.bambooforest.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.sparta.bambooforest.exception.ErrorCode.COMMENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;


    @Transactional
    public CommentResponseDto createComment(Long postId, CommentRequestDto commentRequestDto, User user) {
        Post post = postService.isExistBoard(postId);
        Comment comment = new Comment(commentRequestDto, user, post);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    @Transactional //진짜 중요!! 없으면 commit이 안됨
    public CommentResponseDto update(Long id, CommentRequestDto commentRequestDto, User user) {
        Comment comment = getComment(id);
        checkComment(id,user);
        comment.update(commentRequestDto);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public ResponseEntity<String> delete(Long id, User user){
        getComment(id);
        checkComment(id,user);
        commentRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
    }

    private Comment getComment(Long id){
        return commentRepository.findById(id).orElseThrow(
                ()-> new CustomException(COMMENT_NOT_FOUND)
        );
    }

    private void checkComment(Long id, User user) {
        if (user.getRole() == UserRoleEnum.ADMIN) return; //유저에서 롤 가져오기
        commentRepository.findByIdAndUser(id, user).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_AUTHOR) //global exception으로 처리
        );
    }
}

