package com.sparta.bambooforest.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private boolean admin= false;
    private String adminToken = "";

    // DONE: 예외처리 및 유효성 검사(?) 서비스 단으로 통일
//    @Size(min = 6, max = 50, message = "이메일 주소는 6~50자 이내로 입력해주세요.")
//    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "올바른 이메일 주소를 입력해주세요.")
    private String email;

//    @Size(min = 4, max = 10, message = "username은 최소 4자 이상, 10자 이하이어야 합니다.")
//    @Pattern(regexp = "^[a-z0-9]+$", message = "username은 알파벳 소문자(a~z), 숫자(0~9)로 구성되어야 합니다.")
    private String username;

//    @Size(min = 8, max = 15, message = "password는 최소 8자 이상, 15자 이하이어야 합니다.")
//    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+$", message = "password는 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자로만 구성되어야 합니다.")
    private String password;
}
