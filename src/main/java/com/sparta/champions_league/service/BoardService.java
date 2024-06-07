package com.sparta.champions_league.service;

import com.sparta.champions_league.dto.BoardRequestDto;
import com.sparta.champions_league.dto.BoardResponseDto;
import com.sparta.champions_league.dto.BoardUpdateDto;
import com.sparta.champions_league.entity.Board;
import com.sparta.champions_league.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;


    public BoardResponseDto getBoard(Long boardNum) {
        return boardRepository.findById(boardNum)
                .map(BoardResponseDto::new)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
    }

    public List<BoardResponseDto> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
    }

    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board();
        board.setTitle(requestDto.getTitle());
        board.setContent(requestDto.getContent());
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    @Transactional
    public BoardUpdateDto updateBoard(Long boardNum, BoardRequestDto requestDto) {
       Board board = boardRepository.findById(boardNum).orElseThrow(
                () -> new IllegalArgumentException("선택한 게시물이 없습니다."));

        board.setTitle(requestDto.getTitle());
        board.setContent(requestDto.getContent());
        Board updatedBoard = boardRepository.save(board);
        return new BoardUpdateDto(updatedBoard);
    }
}
