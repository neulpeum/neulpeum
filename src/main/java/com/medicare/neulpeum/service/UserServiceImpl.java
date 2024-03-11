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
            Optional<UserInfo> optionalUserInfo = userRepository.findByPassword(encryptedCurrentPassword);
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
                throw new IllegalArgumentException("비밀번호에 일치하는 유저가 존재하지 않습니다. 입력한 password : " + userPasswordUpdateRequestDto.getCurrentPassword());
            }
        } catch (Exception e) {
            log.error("유저 비밀번호 변경 중 오류 발생 {}", e.getMessage());
            throw new RuntimeException("유저 비밀번호 변경 중 오류 발생 " + e.getMessage());
        }
    }

    @Override
    public void adminUpdate(AdminUpdateRequestDto adminUpdateRequestDto) {
        try {
            Optional<UserInfo> optionalAdminInfo = userRepository.findByPassword(adminUpdateRequestDto.getCurrentPassword());
            if (optionalAdminInfo.isPresent()) {
                UserInfo userInfo = optionalAdminInfo.get();
                //주어진 DTO에서 새로운 정보 추출하여 업데이트
                userInfo.updatePassword(passwordEncoder, adminUpdateRequestDto.getNewPassword());

                //업데이트 된 정보 저장
                userRepository.save(userInfo);
            } else {
                throw new IllegalArgumentException("입력한 비밀번호에 일치하는 관리자 정보를 찾을 수 없습니다. 입력한 password : " + adminUpdateRequestDto.getCurrentPassword());
            }
        } catch (Exception e) {
            log.error("관리자 비밀번호 변경 중 오류 발생 {}", e.getMessage());
            throw new RuntimeException("관리자 비밀번호 변경 중 오류 발생" + e.getMessage());
        }
    }
}
