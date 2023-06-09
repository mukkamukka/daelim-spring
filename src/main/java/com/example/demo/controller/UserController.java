package com.example.demo.controller;

import com.example.demo.dto.user.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> userList() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllByUser());
    }

    @GetMapping("/listOrderByName")
    public ResponseEntity<List<UserDto>> userListOrderByName() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByOrderByName());
    }
}
