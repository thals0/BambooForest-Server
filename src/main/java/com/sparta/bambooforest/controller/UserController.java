package com.sparta.bambooforest.controller;


import com.sparta.bambooforest.dto.LoginRequestDto;
import com.sparta.bambooforest.dto.SignupRequestDto;
import com.sparta.bambooforest.entity.User;
import com.sparta.bambooforest.jwt.JwtUtil;
import com.sparta.bambooforest.service.UserService;
import com.sparta.bambooforest.service.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    // 모든 유저 확인
    @GetMapping("/")
    public List<User> getUsers() {
        return userService.getUsers();
    }
    // 회원 가입( @Valid 사용)
//    @ResponseBody // @RestController를 사용했기 때문에 이미 @ResponseBody가 들어감
//    @PostMapping("/signup")
//    public ResponseEntity<String> signup(@Valid @RequestBody final SignupRequestDto signupRequestDto, Errors errors){
//        if(errors.hasErrors()){
//            // 유효성 통과 못한 필드와 메시지를 핸들링
//            Map<String, String> validatorResult = userService.validateHandling(errors);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validatorResult.toString());
//        }
//        userService.signup(signupRequestDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
//    }

    // 회원 가입( @Valid 사용 X)
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody final SignupRequestDto signupRequestDto){
        userService.signup(signupRequestDto);
        //  ResponseMessage.Customexception.. 인가 어쩌구 적용해보기
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
    }

    // 로그인
//    @ResponseBody
    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        userService.signin(loginRequestDto, response);
        return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
    }

}

//return ResponseEntity.status(HttpStatus.CREATED).body("댓글 작성 성공");
