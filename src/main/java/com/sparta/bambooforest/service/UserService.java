package com.sparta.bambooforest.service;

import com.sparta.bambooforest.dto.LoginRequestDto;
import com.sparta.bambooforest.dto.SignupRequestDto;
import com.sparta.bambooforest.entity.User;
import com.sparta.bambooforest.entity.UserRoleEnum;
import com.sparta.bambooforest.jwt.JwtUtil;
import com.sparta.bambooforest.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// jdk
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.validation.annotation.Validated;

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
    public void signup(@Valid final SignupRequestDto signupRequestDto) {
        String email = signupRequestDto.getEmail();
        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

//        checkNicknameDulicated(signupRequestDto.getUsername());

        // 중복 아이디 불가
        Optional<User> found = userRepository.findByEmail(email);
        if(found.isPresent()){
            // TODO: statuscode 400으로 날리기
            throw new IllegalArgumentException("중복된 username 입니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(email, username, password, role);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public void signin(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));

    }

    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
