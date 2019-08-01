package com.epam.webchat.leonidivanov.datalayer.entity;

import com.epam.webchat.leonidivanov.datalayer.entity.enums.UserRole;
import com.epam.webchat.leonidivanov.datalayer.entity.enums.UserStatus;
import lombok.Data;


import javax.persistence.*;

@Entity
@Table(name = "USERS")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long userId;
    @Column(name = "LOGIN", unique = true, nullable = false)
    private String login;
    @Column(name = "PASSWORD", unique = true, nullable = false)
    private String password;
    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;
    @Column(name = "FULL_NAME", unique = true, nullable = false)
    private String fullName;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

}
