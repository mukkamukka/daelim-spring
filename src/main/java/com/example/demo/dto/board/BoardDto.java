package com.example.demo.dto.board;

import com.example.demo.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private String title;
    private String contents;
    private String userName;
    private String userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BoardDto(Board board) {
    }
}
