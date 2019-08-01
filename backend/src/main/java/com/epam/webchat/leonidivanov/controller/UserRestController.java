package com.epam.webchat.leonidivanov.controller;

import com.epam.webchat.leonidivanov.controller.dto.UserDto;
import com.epam.webchat.leonidivanov.datalayer.entity.User;
import com.epam.webchat.leonidivanov.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class describes controller for work with User entity
 */
@RestController
@RequestMapping("/user")
public class UserRestController {
    private final UserService userService;

    private final ModelMapper modelMapper;

    public UserRestController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    /**
     * Returns all users list
     *
     * @return users list
     */
    @GetMapping("/list")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Return user data by login
     *
     * @param userId -- user login
     * @return user data
     */
    @Secured({"ROLE_ADMINISTRATOR", "ROLE_USER"})
    @GetMapping("/{userId}")
    public UserDto getUserData(@PathVariable Long userId) {
        return convertToDto(userService.getUserData(userId));
    }

    /**
     * Adds new user
     *
     * @param addingUser -- new user data
     * @return added user
     */
    @PostMapping("/register")
    public UserDto registerUser(@RequestBody UserDto addingUser) {
        return convertToDto(userService.register(convertToEntity(addingUser)));
    }

    /**
     * Changes user data
     *
     * @param changedUser -- changing data
     * @return changed user
     */
    @Secured({"ROLE_ADMINISTRATOR", "ROLE_USER"})
    @PutMapping
    public UserDto changeUser(@RequestBody UserDto changedUser) {
        return convertToDto(userService.changeUser(convertToEntity(changedUser)));
    }

    /**
     * Deletes user data
     *
     * @param userId --deleting user id
     */
    @Secured("ROLE_ADMINISTRATOR")
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    /**
     * Kicks user
     *
     * @param userId --kicking user id
     */
    @Secured("ROLE_ADMINISTRATOR")
    @DeleteMapping("/kick/{userId}")
    public void kickUser(@PathVariable Long userId) {
        userService.kickUser(userId);
    }

    /**
     * Bans user
     *
     * @param userId --kicking user id
     */
    @Secured("ROLE_ADMINISTRATOR")
    @DeleteMapping("/ban/{userId}")
    public void banUser(@PathVariable Long userId) {
        userService.banUser(userId);
    }


    /**
     * Converts User entity to User DTO
     *
     * @param user -- user entity
     * @return user DTO
     */
    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    /**
     * Converts User DTO to User entity
     *
     * @param userDto -- user DTO
     * @return user DTO
     */
    private User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }


}
