package com.bridgelabz.fundoonotes.service.impl;

import com.bridgelabz.fundoonotes.dto.request.LoginRequestDto;
import com.bridgelabz.fundoonotes.dto.request.UserRegisterRequestDto;
import com.bridgelabz.fundoonotes.dto.response.LoginResponseDto;
import com.bridgelabz.fundoonotes.dto.response.UserResponseDto;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.exception.UserAlreadyExistsException;
import com.bridgelabz.fundoonotes.exception.UserNotFoundException;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.UserService;
import com.bridgelabz.fundoonotes.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public UserResponseDto register(UserRegisterRequestDto requestDto) {
        log.info("Registering user with email: {}", requestDto.getEmail());

        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email already registered: " + requestDto.getEmail());
        }

        User user = new User();
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        User saved = userRepository.save(user);
        log.debug("User saved with id: {}", saved.getId());

        return new UserResponseDto(saved.getId(), saved.getFirstName(), saved.getLastName(), saved.getEmail(), "User registered successfully");
    }

    @Override
    public LoginResponseDto login(LoginRequestDto requestDto) {
        log.info("Login attempt for email: {}", requestDto.getEmail());

        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + requestDto.getEmail()));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getId());
        log.debug("Token generated for userId: {}", user.getId());

        return new LoginResponseDto(token, "Login successful");
    }
}