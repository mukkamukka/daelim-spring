package com.example.demo.controller;

import com.example.demo.dto.board.BoardDto;
import com.example.demo.dto.board.BoardResponseDto;
import com.example.demo.dto.board.ChangeBoardDto;
import com.example.demo.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //게시글수정-본인이 작성한 글만
    @PutMapping
    public ResponseEntity<BoardResponseDto> updateBoard(@RequestBody ChangeBoardDto changeBoardDto, Principal principal) throws Exception{
        BoardResponseDto boardResponseDto = boardService.changeProductName(changeBoardDto.getId(), changeBoardDto.getTitle(), changeBoardDto.getContents(), principal.getName());
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }

    //게시글 등록
    //ADMIN,USER
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardDto boardDto, Principal principal) {
        log.info("controller1");
        BoardResponseDto boardResponseDto = boardService.saveProduct(boardDto, principal.getName());
        log.info("controller2");
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }

    //게시글삭제-본인이 작성한 글만
    @DeleteMapping
    public ResponseEntity<String> deleteBoard(Long id, Principal principal) throws Exception {
        boardService.deleteProduct(id, principal.getName());
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

    //게시판 리스트
    //ANY
    @GetMapping("/list")
    public ResponseEntity<List<BoardDto>> boardList() {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.findAllByProduct());

    }

    //게시판 리스트-작성일자 순,내림차순(작성자중복가능)
    //ANY
    @GetMapping("/listOrderByCreatedAt")
    public ResponseEntity<List<BoardDto>> boardListOrderByCreatedAtDesc() {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.findByOrderByCreatedAtDesc());
    }

    //게시글 리스트-작성자를 통해 가져오기
    //ANY
    @GetMapping("/byUserId")
    public ResponseEntity<List<BoardDto>> boardListOrderByUserId(String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.findByUserId(userId));
    }

    //게시글 정보-아이디를 통해가져오기
    //ANY
    @GetMapping("/")
    public ResponseEntity<BoardDto> boardOrderById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.findBoardById(id));
    }
}
