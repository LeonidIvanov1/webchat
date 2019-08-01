package com.epam.webchat.leonidivanov.controller.dto;

import lombok.Data;

@Data
public class AuthenticationResponseDto {
    private String username;
    private Long userID;
    private String token;
}
