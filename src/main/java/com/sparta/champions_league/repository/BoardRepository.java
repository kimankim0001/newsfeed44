package com.sparta.champions_league.repository;

import com.sparta.champions_league.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
