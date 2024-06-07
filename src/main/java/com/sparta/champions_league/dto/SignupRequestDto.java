package com.sparta.champions_league.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private Long user_id;
    private String user_name;
    private String password;
    private String email;
    private String comment;
    private Integer team;
    private boolean admin = false;
    private String adminToken = "";
}