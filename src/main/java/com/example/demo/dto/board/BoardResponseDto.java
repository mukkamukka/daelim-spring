package com.example.demo.dto.board;

import com.example.demo.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {

    private Long id;
    private String title;
    private String contents;
    private String userId;
    private String userName;

    public BoardResponseDto(Board board) {
    }
}
