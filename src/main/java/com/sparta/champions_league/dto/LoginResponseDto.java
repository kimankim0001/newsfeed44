package com.sparta.champions_league.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDto { // POSTMAN 에서 회원정보 제대로 나오는지 확인하기 위해서 추가
    private String userId;
    private String password;
    private String email;
    private String userName;
    private String token;
}