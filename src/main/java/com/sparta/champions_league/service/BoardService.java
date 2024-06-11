package com.sparta.champions_league.service;

import com.sparta.champions_league.dto.BoardRequestDto;
import com.sparta.champions_league.dto.BoardResponseDto;
import com.sparta.champions_league.dto.BoardUpdateDto;
import com.sparta.champions_league.entity.Board;
import com.sparta.champions_league.entity.User;
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

    //Board 단건 조회
    public BoardResponseDto getBoard(Long boardNum) {
        return boardRepository.findById(boardNum)
                .map(BoardResponseDto::new)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
    }

    //Board 전체 조회
    public List<BoardResponseDto> getAllBoards() {
        List<Board> boards = boardRepository.findAllByOrderByCreatedAtDesc();
        if (boards.isEmpty()) {
            throw new IllegalArgumentException("먼저 작성하여 소식을 알려보세요!");
        }
        return boards.stream()
                .map(BoardResponseDto::new)
                .toList();
    }

    //Board 생성
    public BoardResponseDto createBoard(BoardRequestDto requestDto, User user) {
        Board board = new Board(requestDto.getTitle(), requestDto.getContent(),user);
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    //Board 수정
    @Transactional
    public BoardUpdateDto updateBoard(Long boardNum, BoardRequestDto requestDto, User user) {
        Board board = boardRepository.findById(boardNum).orElseThrow(
                () -> new IllegalArgumentException("선택한 게시물이 없습니다."));

        board.update(requestDto.getTitle(), requestDto.getContent(),user);
        return new BoardUpdateDto(board);
    }

    //선택 Board 삭제
    public void deleteBoard(Long boardNum) {
        Board board = boardRepository.findById(boardNum).orElseThrow(
                () -> new IllegalArgumentException("선택한 게시물이 없습니다."));
        boardRepository.delete(board);
    }
}