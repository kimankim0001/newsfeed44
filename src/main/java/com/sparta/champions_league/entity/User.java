package com.sparta.champions_league.entity;

import com.sparta.champions_league.entity.UserRoleEnum;
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
    private Long user_id;

    @Column(nullable = false, unique = true)
    private String user_name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false, unique = true)
    private Integer team;


    public User(Long user_id, String user_name, String password, String email,String comment,Integer team ) {
        this.user_id=user_id;
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.comment = comment;
        this.team = team;


    }
}