package com.epam.webchat.leonidivanov.controller.dto;

import com.epam.webchat.leonidivanov.datalayer.entity.enums.UserRole;
import com.epam.webchat.leonidivanov.datalayer.entity.enums.UserStatus;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class UserDto {
    @Null
    private Long userId;
    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String fullName;
    @Null
    private UserStatus userStatus;
    @Null
    private UserRole userRole;
}
