package com.epam.webchat.leonidivanov.datalayer.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
