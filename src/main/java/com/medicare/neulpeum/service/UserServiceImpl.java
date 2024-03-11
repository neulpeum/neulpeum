package com.medicare.neulpeum.service;

import com.medicare.neulpeum.Repository.UserRepository;
import com.medicare.neulpeum.domain.entity.UserInfo;
import com.medicare.neulpeum.dto.AdminUpdateRequestDto;
import com.medicare.neulpeum.dto.UserPasswordUpdateRequestDto;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void update(UserPasswordUpdateRequestDto userPasswordUpdateRequestDto) {
        try {
            // 입력한 비밀번호를 암호화하여 유저 정보 찾아오기
            String encryptedCurrentPassword = passwordEncoder.encode(userPasswordUpdateRequestDto.getCurrentPassword());
            System.out.println(encryptedCurrentPassword);
            //currentPassword를 encoding하면 DB에 담겨있는 암호화된 비밀번호와 달라서 username으로 DB에서 찾아와야함
            Optional<UserInfo> optionalUserInfo = userRepository.findByUsername("user");
            if (optionalUserInfo.isPresent()) {
                UserInfo userInfo = optionalUserInfo.get();
                // DB에서 가져온 암호화된 비밀번호와 입력한 암호화된 비밀번호 비교
                if (passwordEncoder.matches(userPasswordUpdateRequestDto.getCurrentPassword(), userInfo.getPassword())) {
                    // 주어진 DTO에서 새로운 비밀번호 추출하여 업데이트
                    userInfo.updatePassword(passwordEncoder, userPasswordUpdateRequestDto.getNewPassword());

                    // 업데이트 된 정보 저장
                    userRepository.save(userInfo);
                } else {
                    throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
                }
            } else {
                throw new IllegalArgumentException("username이 user인 계정이 존재하지 않습니다.");
            }
        } catch (Exception e) {
            log.error("유저 비밀번호 변경 중 오류 발생 {}", e.getMessage());
            throw new RuntimeException("유저 비밀번호 변경 중 오류 발생 " + e.getMessage());
        }
    }

    @Override
    public void adminUpdate(AdminUpdateRequestDto adminUpdateRequestDto) {
        try {
            // 입력한 비밀번호를 암호화하여 유저 정보 찾아오기
            String encryptedCurrentPassword = passwordEncoder.encode(adminUpdateRequestDto.getCurrentPassword());
            System.out.println(encryptedCurrentPassword);
            //currentPassword를 encoding하면 DB에 담겨있는 암호화된 비밀번호와 달라서 username으로 DB에서 찾아와야함
            Optional<UserInfo> optionalUserInfo = userRepository.findByUsername("admin");
            if (optionalUserInfo.isPresent()) {
                UserInfo userInfo = optionalUserInfo.get();
                // DB에서 가져온 암호화된 비밀번호와 입력한 암호화된 비밀번호 비교
                if (passwordEncoder.matches(adminUpdateRequestDto.getCurrentPassword(), userInfo.getPassword())) {
                    // 주어진 DTO에서 새로운 비밀번호 추출하여 업데이트
                    userInfo.updatePassword(passwordEncoder, adminUpdateRequestDto.getNewPassword());

                    // 업데이트 된 정보 저장
                    userRepository.save(userInfo);
                } else {
                    throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
                }
            } else {
                throw new IllegalArgumentException("username이 admin인 계정이 존재하지 않습니다.");
            }
        } catch (Exception e) {
            log.error("관리자 비밀번호 변경 중 오류 발생 {}", e.getMessage());
            throw new RuntimeException("관리자 비밀번호 변경 중 오류 발생 " + e.getMessage());
        }
    }
}
