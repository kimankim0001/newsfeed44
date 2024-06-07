package com.sparta.champions_league.repository;

import com.sparta.champions_league.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<com.sparta.champions_league.entity.User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);


}
