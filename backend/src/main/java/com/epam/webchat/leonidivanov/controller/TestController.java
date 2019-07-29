package com.epam.webchat.leonidivanov.controller;

import com.epam.webchat.leonidivanov.datalayer.entity.User;
import com.epam.webchat.leonidivanov.datalayer.entity.enums.UserRole;
import com.epam.webchat.leonidivanov.datalayer.entity.enums.UserStatus;
import com.epam.webchat.leonidivanov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class TestController {
    @Autowired
    private UserService userService;

    @RequestMapping("/welcome")
    public void test(HttpServletRequest request,
                     HttpServletResponse response) {
        System.out.println("WELCOME!");
        User user = new User();
        user.setLogin("1");
        user.setPassword("2");
        user.setEmail("3");
        user.setFullName("4");
        user.setUserRole(UserRole.USER);
        user.setUserStatus(UserStatus.OFFLINE);
        userService.addUser(user);
    }
}
