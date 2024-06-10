package com.sparta.champions_league.dto;

import lombok.Getter;

@Getter
public class ProfileResponseDto {

    private String userId;
    private String userName;
    private String email;
    private String password;

    public ProfileResponseDto(String userId, String userName, String email, String password) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
