package com.example.demo.dao.impl;

import com.example.demo.dao.BoardDao;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class BoardDaoImpl implements BoardDao {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardDaoImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board updateBoard(Long id, String title, String contents, String name) throws Exception {
        Board selectBoard = boardRepository.findBoardById(id);
        Board updateBoard;
        selectBoard.setContents(contents);
        selectBoard.setTitle(title);
        selectBoard.setUpdatedAt(LocalDateTime.now());
        updateBoard = boardRepository.save(selectBoard);
        return updateBoard;
    }

    @Override
    public Board insertBoard(Board board) {
        log.info("dao");
        return boardRepository.save(board);
    }

    @Override
    public void deleteBoard(Long id, String name) throws Exception {
        Optional<Board> selectBoard = boardRepository.findById(id);
        if (selectBoard.isPresent()) {
            Board board = selectBoard.get();
            boardRepository.delete(board);
        } else {
            throw new Exception();
        }
    }

    @Override
    public List<Board> findAllByBoard() {
        return boardRepository.findAllByBoard();
    }

    @Override
    public List<Board> findByOrderByCreatedAtDesc() {
        return boardRepository.findByOrderByCreatedAtDesc();
    }

    @Override
    public List<Board> findByUserId(String userId) {
        return boardRepository.findByUserId(userId);
    }

    @Override
    public Board findBoardById(Long id) {
        return boardRepository.findBoardById(id);
    }
}
