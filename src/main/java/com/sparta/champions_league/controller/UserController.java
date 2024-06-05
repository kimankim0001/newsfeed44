package com.sparta.champions_league.controller;

import com.sparta.champions_league.dto.SignupRequestDto;
import com.sparta.champions_league.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login"; //html 파일 반환
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup"; //html 파일 반환
    }

    @PostMapping("/user/signup")
    public String signup(SignupRequestDto requestDto) {
        userService.signup(requestDto);
        return "redirect:/api/user/login-page";
    }
}