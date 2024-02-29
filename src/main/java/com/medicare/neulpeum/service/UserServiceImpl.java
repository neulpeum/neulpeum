package com.medicare.neulpeum.service;

import com.medicare.neulpeum.Repository.UserRepository;
import com.medicare.neulpeum.domain.entity.UserInfo;
import com.medicare.neulpeum.dto.AdminUpdateRequestDto;
import com.medicare.neulpeum.dto.UserPasswordUpdateRequestDto;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public void update(UserPasswordUpdateRequestDto userPasswordUpdateRequestDto) {
        try {
            Optional<UserInfo> optionalUserInfo = userRepository.findByUsername(userPasswordUpdateRequestDto.getUsername());
            if (optionalUserInfo.isPresent()) {
                UserInfo userInfo = optionalUserInfo.get();
                //주어진 DTO에서 새로운 정보 추출하여 업데이트
                userInfo.setPassword(userPasswordUpdateRequestDto.getPassword());

                //업데이트 된 정보 저장
                userRepository.save(userInfo);
            } else {
                throw new IllegalArgumentException("유저 정보를 찾을 수 없습니다. username:" + userPasswordUpdateRequestDto.getUsername());
            }
        } catch (Exception e) {
            log.error("유저 비밀번호 변경 중 오류 발생 {}", e.getMessage());
            throw new RuntimeException("유저 비밀번호 변경 중 오류 발생 " + e.getMessage());
        }
    }

    @Override
    public void adminUpdate(AdminUpdateRequestDto adminUpdateRequestDto) {
        try {
            Optional<UserInfo> optionalAdminInfo = userRepository.findByUsername(adminUpdateRequestDto.getUsername());
            if (optionalAdminInfo.isPresent()) {
                UserInfo userInfo = optionalAdminInfo.get();
                //주어진 DTO에서 새로운 정보 추출하여 업데이트
                userInfo.setUsername(adminUpdateRequestDto.getUsername());
                userInfo.setPassword(adminUpdateRequestDto.getPassword());

                //업데이트 된 정보 저장
                userRepository.save(userInfo);
            } else {
                throw new IllegalArgumentException("관리자 정보를 찾을 수 없습니다. username: " + adminUpdateRequestDto.getUsername());
            }
        } catch (Exception e) {
            log.error("관리자 아이디 비밀번호 변경 중 오류 발생 {}", e.getMessage());
            throw new RuntimeException("관리자 비밀번호 변경 중 오류 발생" + e.getMessage());
        }
    }
}
