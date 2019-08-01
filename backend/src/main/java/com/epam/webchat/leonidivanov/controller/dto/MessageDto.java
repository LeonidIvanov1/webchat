package com.epam.webchat.leonidivanov.controller.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDto {
    private Long id;
    private String text;
    private String author;
    private Date sendingTime;
}
