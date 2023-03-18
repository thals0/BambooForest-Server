package com.sparta.bambooforest.service;

import com.sparta.bambooforest.dto.PostRequestDto;
import com.sparta.bambooforest.dto.PostResponseDto;

import com.sparta.bambooforest.entity.Post;
import com.sparta.bambooforest.entity.User;
import com.sparta.bambooforest.entity.UserRoleEnum;
import com.sparta.bambooforest.exception.CustomException;
import com.sparta.bambooforest.exception.ErrorCode;
import com.sparta.bambooforest.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto, User user) {
        Post post = new Post(postRequestDto, user);
        postRepository.save(post);
        return new PostResponseDto(post);

    }

    public List<PostResponseDto> getAllPosts() {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> postResponseDtoList =new ArrayList<>();
        for(Post post: postList){
            PostResponseDto postResponseDto = new PostResponseDto(post);
            postResponseDtoList.add(postResponseDto);
        }
        return postResponseDtoList;
    }

    @Transactional
    public PostResponseDto getPost(Long id, User user) {
        Post post = isExistBoard(id);
        checkPostRole(id, user);
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto, User user) {
        Post post = isExistBoard(id);    // 게시글 확인 및 조회
        checkPostRole(id, user);  // 권한 확인 (본인 확인)
        post.update(postRequestDto.getTitle(), postRequestDto.getContent());
        return new PostResponseDto(post);
    }

    @Transactional
    public void deletePost(Long id, User user) {
        checkPostRole(id, user);
        postRepository.deleteById(id);
    }

    private void checkPostRole(Long id, User user) {
        if (user.getRole() == UserRoleEnum.ADMIN) return; //유저에서 롤 가져오기
        postRepository.findByPostIdAndUser(id, user).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND) //global exception으로 처리
        );
    }

    public Post isExistBoard(Long id){
        return postRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.POST_NOT_FOUND) //global exception으로 처리
        );
    }



}
