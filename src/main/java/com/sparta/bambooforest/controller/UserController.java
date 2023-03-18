package com.sparta.bambooforest.controller;

import com.sparta.bambooforest.dto.LoginRequestDto;
import com.sparta.bambooforest.dto.SignupRequestDto;
import com.sparta.bambooforest.entity.User;
import com.sparta.bambooforest.exception.RestApiException;
import com.sparta.bambooforest.jwt.JwtUtil;
import com.sparta.bambooforest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    // 모든 유저 확인
    @GetMapping("/")
    public List<User> getUsers() {
        return userService.getUsers();
    }
    // 회원 가입
    @ResponseBody
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody final SignupRequestDto signupRequestDto, BindingResult result){
        userService.signup(signupRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
    }

    @ResponseBody
    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        userService.signin(loginRequestDto, response);
        return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
    }

}
