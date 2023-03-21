package com.sparta.bambooforest.service;

import com.sparta.bambooforest.dto.LoginRequestDto;
import com.sparta.bambooforest.dto.SignupRequestDto;
import com.sparta.bambooforest.entity.User;
import com.sparta.bambooforest.entity.UserRoleEnum;
import com.sparta.bambooforest.exception.CustomException;
import com.sparta.bambooforest.jwt.JwtUtil;
import com.sparta.bambooforest.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// jdk
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;

import static com.sparta.bambooforest.exception.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Validated
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    // 관리자 토큰
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    // 회원가입
    @Transactional
    public void signup(final SignupRequestDto signupRequestDto) {
        String email = signupRequestDto.getEmail();
        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        // DONE: @Valid를 사용하지 않고 유효성 검사하기
        if(!UserValidator.emailValidate(email)){
            throw new CustomException(INVALID_EMAIL);
        }

        if(!UserValidator.usernameValidate(username)){
            throw new CustomException(INVALID_USERNAME);
        }

        if(!UserValidator.passwordValidate(signupRequestDto.getPassword())){
            throw new CustomException(INVALID_PASSWORD);
        }

        // 중복 아이디 불가
        Optional<User> found = userRepository.findByEmail(email);
        if(found.isPresent()){
            // DONE: CustomException 쓰기
            throw new CustomException(DUPLICATE_USER);
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new CustomException(WRONG_ADMIN_TOKEN);
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(email, username, password, role);
        userRepository.save(user);
    }

    // DONE: @Valid 에러 처리
    // @Valid 사용시 예외 처리 컨벤션을 지키기 힘드므로 사용하지 않는 방법으로 변경함
    // 회원가입 시, 유효성 체크
//    public Map<String, String> validateHandling(Errors errors) {
//        Map<String, String> validatorResult = new HashMap<>();
//
//        for (FieldError error : errors.getFieldErrors()) {
//            String validKeyName = String.format("valid_%s", error.getField());
//            validatorResult.put(validKeyName, error.getDefaultMessage());
//        }
//
//        return validatorResult;
//    }

    @Transactional(readOnly = true)
    public void signin(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        // DONE : exception 수정
        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> new CustomException(USER_NOT_FOUND)
        );

        // 비밀번호 확인
        // DONE : exception 수정
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new CustomException(NOT_PROPER_PASSWORD);
        }

        // http 응답에 헤더 추가
        // 토큰 생성 후 AUTHORIZATION_HEADER라는 이름의 헤더로 응답에 추가
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getEmail(), user.getRole()));

    }

    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
