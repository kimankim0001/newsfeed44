package com.sparta.champions_league.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNum;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Integer team;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;



    public User(String userId,String userName, String password, String email, UserRoleEnum role, String comment,int team) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.team = team;
        this.comment = comment;
    }

    public void updateUserProfile(String userId, String userName, String email, String comment, String password){
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.comment = comment;
        this.password = password;
    }
}