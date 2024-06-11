package com.sparta.champions_league.dto;

import com.sparta.champions_league.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private Long boardNum;
    private String title;
    private String content;

    public BoardResponseDto(Board board) {
        this.boardNum = board.getBoardNum();
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
