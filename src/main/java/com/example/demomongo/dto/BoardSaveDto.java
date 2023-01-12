package com.example.demomongo.dto;

import com.example.demomongo.domain.Board;
import lombok.Data;

@Data
public class BoardSaveDto {
    private String title;
    private String content;

    public Board toEntity() {
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        return board;
    }
}
