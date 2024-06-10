package com.sparta.champions_league.controller;

import com.sparta.champions_league.dto.*;
import com.sparta.champions_league.entity.User;
import com.sparta.champions_league.security.UserDetailsImpl;
import com.sparta.champions_league.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/users/sign")
    public ResponseEntity<String> signup(@RequestBody SignupRequestDto requestDto) {
        userService.signup(requestDto);
        return new ResponseEntity<>("회원가입 성공", HttpStatusCode.valueOf(200));

    }

    // 로그인
    @PostMapping("/users/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto requestDto) {
        LoginResponseDto responseDto = userService.login(requestDto);
        return responseDto;
    }


    // 프로필 단건조회
    @GetMapping("/users/profile/{userNum}")
    public ResponseEntity<ProfileResponseDto> getUser(@PathVariable(value = "userNum") Long userNum) {
        ProfileResponseDto responseDto = userService.getUser(userNum);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    // 회원탈퇴
    @DeleteMapping("/users/{userNum}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "userNum") Long userNum, @RequestBody UserUpdateDto userUpdateDto) {
        userService.deleteUser(userNum, userUpdateDto);
        return new ResponseEntity<>("회원탈퇴 성공", HttpStatusCode.valueOf(200));
    }

    // 회원정보수정
    @PutMapping("/users/profile/{userNum}")
    public ResponseEntity<ProfileResponseDto> updateUser(@PathVariable(value = "userNum") Long userNum, @RequestBody UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok().body(userService.updateUser(userNum, userUpdateDto));
    }
}