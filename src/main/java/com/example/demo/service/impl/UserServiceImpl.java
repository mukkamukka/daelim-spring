package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.user.UserDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserDto> findAllByUser() {
        List<User> users = userDao.findAllByUser();

        return users.stream()
                .map(user -> new UserDto(user.getName(), user.getEmail(), user.getRoles()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findByOrderByName() {
        List<User> users = userDao.findByOrderByName();

        return users.stream()
                .map(user -> new UserDto(user.getName(), user.getEmail(), user.getRoles()))
                .collect(Collectors.toList());
    }
}
