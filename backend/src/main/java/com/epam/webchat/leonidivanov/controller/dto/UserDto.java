package com.epam.webchat.leonidivanov.controller.dto;

import com.epam.webchat.leonidivanov.datalayer.entity.enums.UserRole;
import com.epam.webchat.leonidivanov.datalayer.entity.enums.UserStatus;
import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String login;
    private String password;
    private String email;
    private String fullName;
    private UserStatus userStatus;
    private UserRole userRole;
}
