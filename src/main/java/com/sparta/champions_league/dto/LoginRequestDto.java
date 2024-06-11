package com.sparta.champions_league.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String userId;
    private String userName;
    private String password;
    private String confirmPassword;
}
