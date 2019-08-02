package com.epam.webchat.leonidivanov.controller.dto;

import com.epam.webchat.leonidivanov.datalayer.entity.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponseDto {
    private String username;
    private Long userID;
    private String token;
    private UserRole userRole;
}
