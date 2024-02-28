package com.medicare.neulpeum.Login;

import com.medicare.neulpeum.Repository.UserRepository;
import com.medicare.neulpeum.domain.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo;
        System.out.println("********************************************");
        System.out.println(username);
        if (username == "admin") {
            userInfo = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다."));
        } else {
            userInfo = userRepository.findByUsername("user").orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다."));

        }
//        UserInfo userInfo = userRepository.findByPassword(username).orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다."));

        return User.builder().username(userInfo.getUsername()).password(userInfo.getPassword()).roles(userInfo.getRole().name()).build();
    }
}
