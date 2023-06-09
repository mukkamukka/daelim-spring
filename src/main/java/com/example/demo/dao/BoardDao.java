package com.example.demo.dao;

import com.example.demo.entity.Board;

import java.util.List;

public interface BoardDao {

    Board updateBoard(Long id, String title, String contents, String name) throws Exception;

    Board insertBoard(Board board);

    void deleteBoard(Long id, String name) throws Exception;

    List<Board> findAllByBoard();

    List<Board> findByOrderByCreatedAtDesc();

    List<Board> findByUserId(String userId);

    Board findBoardById(Long id);
}
