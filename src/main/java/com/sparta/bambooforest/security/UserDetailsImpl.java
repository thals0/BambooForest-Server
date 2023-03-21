package com.sparta.bambooforest.security;

import com.sparta.bambooforest.entity.User;
import com.sparta.bambooforest.entity.UserRoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// Authentication을 담고있는 UserDetails 인터페이스를 상속
// SecurityContextHolder에 담을 Authentication 객체를 만들 때 사용
public class UserDetailsImpl implements UserDetails {

    private final User user;
    private final String email;

    public UserDetailsImpl(User user, String email) {
        this.user = user;
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    // 사용자의 권한 정보 가져옴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UserRoleEnum role = user.getRole();
        String authority = role.getAuthority();

        // 권한 정보를 문자열 형태로 표현
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}