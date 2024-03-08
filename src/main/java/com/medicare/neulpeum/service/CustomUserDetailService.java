package com.medicare.neulpeum.service;

import com.medicare.neulpeum.Repository.UserRepository;
import com.medicare.neulpeum.auth.CustomUserDetail;
import com.medicare.neulpeum.domain.entity.UserInfo;
import com.medicare.neulpeum.dto.UserRequestDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailService(UserRepository userRepository, @Lazy AuthenticationManager authenticationManager, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    //시큐리티 내부의 로그인 프로세스중 인증처리를 하는 메소드 중 하나
    //이 메서드를 오버라이드하여 데이터베이스의 유저 정보를 가져와서
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userRepository.findByUsername(username).orElse(null);

        if (userInfo == null) {
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }

        return new CustomUserDetail(userInfo);
    }

    //헤더에 담긴
    public void login(HttpServletRequest request, HttpServletResponse response,
                      UserRequestDto requestDto) {
        Authentication authentication = authenticateUser(requestDto.getUsername(), requestDto.getPassword());
        setAuthenticationInContext(authentication);
        createSessionAndSetCookie(request, response);
    }

    private Authentication authenticateUser(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, password, new ArrayList<>());

        try {
            return authenticationManager.authenticate(authentication);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    private void setAuthenticationInContext(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void createSessionAndSetCookie(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setPath("/");
        cookie.setMaxAge(30000 * 60);
        response.addCookie(cookie);
    }
}
