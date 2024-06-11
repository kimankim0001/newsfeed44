package com.sparta.champions_league.repository;

import com.sparta.champions_league.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
        Optional<User> findByEmail(String email);
        Optional<User> findByUserId(String userId);
        Optional<Object> findByPassword(String password);

}
