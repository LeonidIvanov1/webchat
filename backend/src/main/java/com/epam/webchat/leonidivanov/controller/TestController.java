package com.epam.webchat.leonidivanov.controller;

import com.epam.webchat.leonidivanov.datalayer.entity.User;
import com.epam.webchat.leonidivanov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class TestController {
    @Autowired
    UserService userService;

    //@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @Secured("ROLE_ADMINISTRATOR")
    @GetMapping("/go")
    public List<User> testAdmin() {
        return userService.getAllUsers();
    }

}
