package com.epam.webchat.leonidivanov.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

@Data
public class MessageDto {
    @Null
    private Long id;
    @NotNull
    private String text;
    @NotNull
    private Long authorId;
    @Null
    private String authorFullName;
    @Null
    private Date sendingTime;
}
