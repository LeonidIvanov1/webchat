package com.epam.webchat.leonidivanov.controller;

import com.epam.webchat.leonidivanov.controller.dto.UserDto;
import com.epam.webchat.leonidivanov.datalayer.entity.User;
import com.epam.webchat.leonidivanov.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping
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
     * Bans user
     *
     * @param userId --kicking user id
     * @return
     */
    @Secured("ROLE_ADMINISTRATOR")
    @PostMapping("/ban/{userId}")
    public ResponseEntity<UserDto> banUser(@PathVariable Long userId) {
        return new ResponseEntity<>(convertToDto(userService.banUser(userId)), HttpStatus.OK);

    }

    /**
     * Unbans user
     *
     * @param userId --kicking user id
     */
    @Secured("ROLE_ADMINISTRATOR")
    @PostMapping("/unban/{userId}")
    public ResponseEntity<UserDto> unbanUser(@PathVariable Long userId) {
        return new ResponseEntity<>(convertToDto(userService.unbanUser(userId)), HttpStatus.OK);
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
