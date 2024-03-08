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
            //입력한 비밀번호로 유저 정보 찾아오기
            Optional<UserInfo> optionalUserInfo = userRepository.findByPassword(userPasswordUpdateRequestDto.getCurrentPassword());
            if (optionalUserInfo.isPresent()) {
                UserInfo userInfo = optionalUserInfo.get();
                //주어진 DTO에서 새로운 정보 추출하여 업데이트
                userInfo.setPassword(userPasswordUpdateRequestDto.getNewPassword());

                //업데이트 된 정보 저장
                userRepository.save(userInfo);
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
                userInfo.setPassword(adminUpdateRequestDto.getNewPassword());

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
