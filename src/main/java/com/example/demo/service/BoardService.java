package com.example.demo.service;

import com.example.demo.dto.board.BoardDto;
import com.example.demo.dto.board.BoardResponseDto;

import java.util.List;

public interface BoardService {

    BoardResponseDto changeProductName(Long id, String title, String contents, String name) throws Exception;

    BoardResponseDto saveProduct(BoardDto boardDto, String name);

    void deleteProduct(Long id, String name) throws Exception;

    List<BoardDto> findAllByProduct();

    List<BoardDto> findByOrderByCreatedAtDesc();

    List<BoardDto> findByUserId(String userId);

    BoardDto findBoardById(Long id);
}
