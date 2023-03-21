package com.sparta.bambooforest.security;

import com.sparta.bambooforest.entity.User;
import com.sparta.bambooforest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    // loadUserByUsername : 사용자 이름을 받아서, 해당 사용자 정보를 조회하고 'UserDetails' 객체로 반환

    /**
     * methodName : loadUserByUsername
     * description : Context에 담기 위해 UserDetails를 implement한 UserDetailsImpl을 반환
     *
     * @param email
     * @return email를 통해 찾은 UserDetailsImpl 객체
     */
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        return new UserDetailsImpl(user, user.getEmail());
    }

}