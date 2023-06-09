package com.example.demo.service;

import com.example.demo.dto.user.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAllByUser();

    List<UserDto> findByOrderByName();
}
