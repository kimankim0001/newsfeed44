package com.sparta.champions_league.controller;

import com.sparta.champions_league.dto.BoardRequestDto;
import com.sparta.champions_league.dto.BoardResponseDto;
import com.sparta.champions_league.dto.BoardUpdateDto;
import com.sparta.champions_league.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;


    //Board 단건 조회
    @GetMapping("/boards/{boardNum}")
    public BoardResponseDto getBoard(@PathVariable Long boardNum) {
        return boardService.getBoard(boardNum);
    }

    //Board 전체 조회
    @GetMapping("/boards")
    public List<BoardResponseDto> getAllBoards() {
        return boardService.getAllBoards();
    }

    //Board 생성
    @PostMapping("/boards")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto) {
        return boardService.createBoard(requestDto);
    }

    //Board 수정
    @PutMapping("/boards/{boardNum}")
    public BoardUpdateDto updateBoard(@PathVariable Long boardNum, @RequestBody BoardRequestDto requestDto) {
        return boardService.updateBoard(boardNum, requestDto);
    }

    //선택 Board 삭제
    @DeleteMapping("/boards/{boardNum}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardNum) {
        boardService.deleteBoard(boardNum);
        return new ResponseEntity<>("게시물 삭제 되었습니다.", HttpStatusCode.valueOf(200));
    }

}
