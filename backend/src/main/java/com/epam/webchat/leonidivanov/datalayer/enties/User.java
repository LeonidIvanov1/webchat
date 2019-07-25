package com.epam.webchat.leonidivanov.datalayer.enties;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@Data
public class User {
    private long userId;
    private String login;
    private String password;
    private String email;

}
