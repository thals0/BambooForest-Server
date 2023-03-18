package com.sparta.bambooforest.service;

import com.sparta.bambooforest.dto.PostRequestDto;
import com.sparta.bambooforest.dto.PostResponseDto;

import com.sparta.bambooforest.entity.Post;
import com.sparta.bambooforest.entity.User;
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
    public PostResponseDto getPost(Long postId) {
        Post post = isExistBoard(postId);
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto, User user) {
        Post post = isExistBoard(postId);    // 게시글 확인 및 조회
        checkPostRole(postId, user);  // 권한 확인 (본인 확인)
        post.update(postRequestDto.getTitle(), postRequestDto.getContent());
        return new PostResponseDto(post);
    }

    @Transactional
    public void deletePost(Long postId, User user) {
        checkPostRole(postId, user);
        postRepository.deleteById(postId);
    }

    private void checkPostRole(Long postId, User user) {
        if (user.getRole() == UserRoleEnum.ADMIN) return; //유저에서 롤 가져오기
        postRepository.findByPostIdAndUser(postId, user).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND) //global exception으로 처리
        );
    }

    public Post isExistBoard(Long postId){
        return postRepository.findById(postId).orElseThrow(
                () -> new CustomException(ErrorCode.POST_NOT_FOUND) //global exception으로 처리
        );
    }



}
