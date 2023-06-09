package com.example.demo.service;


import com.example.demo.dto.sign.SignInResultDto;
import com.example.demo.dto.sign.SignUpResultDto;

public interface SignService {

    SignUpResultDto signUp(String id, String password, String name, String email, String role);

    SignInResultDto signIn(String id, String password) throws RuntimeException;

}