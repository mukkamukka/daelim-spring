package com.example.demo.service.impl;

import com.example.demo.dao.BoardDao;
import com.example.demo.dto.board.BoardDto;
import com.example.demo.dto.board.BoardResponseDto;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardDao boardDao;

    @Autowired
    public BoardServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    public BoardResponseDto changeProductName(Long id, String title, String contents, String name) throws Exception {
        // 게시글을 조회하고, 작성자 이름을 가져옵니다.
        Board existingBoard = boardDao.findBoardById(id);
        String authorName = existingBoard.getUserId();

        // 현재 사용자의 이름과 작성자 이름을 비교합니다.
        if (!name.equals(authorName)) {
            throw new Exception("게시글을 수정할 수 있는 권한이 없습니다.");
        }

        Board changeBoard = boardDao.updateBoard(id, title, contents, name);

        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setTitle(changeBoard.getTitle());
        boardResponseDto.setContents(changeBoard.getContents());
        boardResponseDto.setId(changeBoard.getId());
        boardResponseDto.setUserId(changeBoard.getUserId());
        boardResponseDto.setUserName(changeBoard.getUserName());
        return boardResponseDto;
    }

    @Override
    public BoardResponseDto saveProduct(BoardDto boardDto, String name) {

        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setContents(boardDto.getContents());
        board.setUserId(name);
        board.setUserName(boardDto.getUserName());
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());
        log.info("service1");
        Board saveBoard = boardDao.insertBoard(board);
        log.info("service2");
        BoardResponseDto boardResponseDto = new BoardResponseDto();

        boardResponseDto.setTitle(saveBoard.getTitle());
        boardResponseDto.setContents(saveBoard.getContents());
        boardResponseDto.setUserId(saveBoard.getUserId());
        boardResponseDto.setUserName(saveBoard.getUserName());
        boardResponseDto.setId(saveBoard.getId());
        return boardResponseDto;
    }

    @Override
    public void deleteProduct(Long id, String name) throws Exception {
        Board existingBoard = boardDao.findBoardById(id);
        String authorName = existingBoard.getUserId();

        if (!name.equals(authorName)) {
            throw new Exception("게시글을 삭제할 수 있는 권한이 없습니다.");
        }
        boardDao.deleteBoard(id, name);
    }

    @Override
    public List<BoardDto> findAllByProduct() {
        List<Board> boards = boardDao.findAllByBoard();

        return boards.stream()
                .map(board -> new BoardDto(board.getTitle(), board.getContents(), board.getUserName(), board.getUserId(), board.getCreatedAt(), board.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public List<BoardDto> findByOrderByCreatedAtDesc() {
        List<Board> boards = boardDao.findByOrderByCreatedAtDesc();

        return boards.stream()
                .map(board -> new BoardDto(board.getTitle(), board.getContents(), board.getUserName(), board.getUserId(), board.getCreatedAt(), board.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public List<BoardDto> findByUserId(String userId) {
        List<Board> boards = boardDao.findByUserId(userId);
        return boards.stream()
                .map(board -> new BoardDto(board.getTitle(), board.getContents(), board.getUserName(), board.getUserId(), board.getCreatedAt(), board.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public BoardDto findBoardById(Long id) {
        Board board = boardDao.findBoardById(id);
        return new BoardDto(board.getTitle(), board.getContents(), board.getUserName(), board.getUserId(), board.getCreatedAt(), board.getUpdatedAt());
    }
}
