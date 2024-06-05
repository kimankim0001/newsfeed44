package com.sparta.champions_league.controller;

import com.sparta.champions_league.dto.BoardRequestDto;
import com.sparta.champions_league.dto.BoardResponseDto;
import com.sparta.champions_league.dto.BoardUpdateDto;
import com.sparta.champions_league.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;


    @GetMapping("/boards/{boardNum}")
    public BoardResponseDto getBoard(@PathVariable Long boardNum) {
        return boardService.getBoard(boardNum);
    }
    @GetMapping("/boards")
    public List<BoardResponseDto> getAllBoards() {
        return boardService.getAllBoards();
    }
}
