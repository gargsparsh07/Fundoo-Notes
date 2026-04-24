package com.bridgelabz.fundoonotes.service;

import com.bridgelabz.fundoonotes.dto.request.LoginRequestDto;
import com.bridgelabz.fundoonotes.dto.request.UserRegisterRequestDto;
import com.bridgelabz.fundoonotes.dto.response.LoginResponseDto;
import com.bridgelabz.fundoonotes.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegisterRequestDto requestDto);
    LoginResponseDto login(LoginRequestDto requestDto);
}