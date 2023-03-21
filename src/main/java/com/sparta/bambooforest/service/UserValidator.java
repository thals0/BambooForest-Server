package com.sparta.bambooforest.service;

import lombok.extern.slf4j.Slf4j;

// 회원가입시 유효성 검사를 위한 클래스 생성
@Slf4j
public class UserValidator {
    // email 유효성 검사
    public static boolean emailValidate(String email) {
        // email 필드가 null이거나 빈 문자열인 경우 false를 반환
        if (email == null || email.isEmpty()) {
            return false;
        }
        // email 필드가 6~50자 이내가 아닌 경우 false를 반환
        if (email.length() < 6 || email.length() > 50) {
            return false;
        }
        // email 필드가 올바른 이메일 형식이 아닌 경우 false를 반환
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            return false;
        }
        return true;
    }

    // username 유효성 검사
    public static boolean usernameValidate(String username) {
        // username 필드가 null이거나 빈 문자열인 경우 false를 반환
        if (username == null || username.isEmpty()) {
            return false;
        }
        // username 필드가 최소 4자 이상, 10자 이하가 아닌 경우 false를 반환
        if (username.length() < 4 || username.length() > 10) {
            return false;
        }
        // username 필드가 알파벳 소문자(a~z), 숫자(0~9)로 구성되어 있지 않은 경우 false를 반환
        if (!username.matches("[a-z0-9]+")) {
            return false;
        }
        return true;
    }

    // password 유효성 검사
    public static boolean passwordValidate(String password) {
        // password 필드가 null이거나 빈 문자열인 경우 false를 반환
        if (password == null || password.isEmpty()) {
            return false;
        }
        // password 필드가 최소 8자 이상, 15자 이하가 아닌 경우 false를 반환
        if (password.length() < 8 || password.length() > 15) {
            return false;
        }
        // password 필드가 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자로만 구성되어 있지 않은 경우 false를 반환
        if (!password.matches("^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+$")) {
            return false;
        }
        return true;
    }
}



