package com.bridgelabz.fundoonotes.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LoginRequestDto {

    @Email(message = "Enter a valid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}