package com.epam.webchat.leonidivanov.controller;

import com.epam.webchat.leonidivanov.datalayer.entity.User;
import com.epam.webchat.leonidivanov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class describes controller for work with User entity
 */
@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private UserService userService;

    /**
     * Returns all users list
     *
     * @return users list
     */
    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Return user data by ID
     *
     * @param userId -- user ID
     * @return user data
     */
    @GetMapping("/{userId}")
    public User getUserData(@PathVariable Long userId) {
        return userService.getUserData(userId);
    }

    /**
     * Adds new user
     *
     * @param addingUser -- new user data
     * @return added user
     */
    @PostMapping
    public User addUser(@RequestBody User addingUser) {
        return userService.register(addingUser);
    }

    /**
     * Changes user data
     *
     * @param changedUser -- changing data
     * @return changed user
     */
    @PutMapping
    public User changeUser(@RequestBody User changedUser) {
        return userService.changeUser(changedUser);
    }

    /**
     * Deletes user data
     *
     * @param userId --deleting user id
     */
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
