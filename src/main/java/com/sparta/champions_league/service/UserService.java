package com.sparta.champions_league.service;

import com.sparta.champions_league.dto.*;
import com.sparta.champions_league.entity.User;
import com.sparta.champions_league.entity.UserRoleEnum;
import com.sparta.champions_league.repository.UserRepository;
import com.sparta.champions_league.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignupRequestDto requestDto) {
        String userId = requestDto.getUserId();
        String userName = requestDto.getUserName();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String comment = requestDto.getComment();
        int team = requestDto.getTeam();

        // 회원 중복 확인
        Optional<User> checkUserid = userRepository.findByUserId(userId);
        if (checkUserid.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // email 중복확인
        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(userId, userName, password, email, role, comment, team);
        userRepository.save(user);
    }

    // 로그인
    public LoginResponseDto login(LoginRequestDto requestDto) {
        Optional<User> user = userRepository.findByUserId(requestDto.getUserId());
        if (user.isEmpty()){
            throw new IllegalArgumentException("일치하는 회원이 없습니다.");
        }
        if(!requestDto.getPassword().equals(requestDto.getConfirmPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        String token = jwtUtil.createToken(user.get().getUserId(), UserRoleEnum.USER);

        LoginResponseDto responseDto = LoginResponseDto.builder()
                .userId(user.get().getUserId())
                .password(user.get().getPassword())
                .email(user.get().getEmail())
                .userName(user.get().getUserName())
                .token(token)
                .build();

        return responseDto;
    }

    // g
    public void deleteUser (Long userNum, UserUpdateDto requestDto) {
        User user = userRepository.findById(userNum).orElseThrow(() -> new IllegalArgumentException("일치하는 회원이 없습니다."));
        if(!requestDto.getPassword().equals(requestDto.getConfirmPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        userRepository.delete(user);

    }


    //사용자 조회
    public ProfileResponseDto getUser(Long userNum) {
        User user = userRepository.findById(userNum).orElseThrow(
                ()-> new IllegalArgumentException("일치하는 회원이 없습니다.")
        );
        return new ProfileResponseDto(user.getUserId(), user.getUserName(), user.getEmail(), user.getPassword(), user.getComment());
    }



    // 프로필수정
    @Transactional
    public ProfileResponseDto updateUser(Long userNum, UserUpdateDto requestDto){
        User user = userRepository.findById(userNum).orElseThrow(() -> new IllegalArgumentException("일치하는 회원이 없습니다."));
        if(!requestDto.getPassword().equals(requestDto.getConfirmPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String checkPassword = passwordEncoder.encode(requestDto.getPassword());

        user.updateUserProfile(requestDto.getUserId(), requestDto.getUserName(), requestDto.getEmail(), requestDto.getComment(), checkPassword);
        return new ProfileResponseDto(user.getUserId(), user.getUserName(), user.getEmail(), user.getComment(), checkPassword);
    }



}