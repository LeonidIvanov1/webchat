package com.epam.webchat.leonidivanov.controller;

import com.epam.webchat.leonidivanov.controller.dto.UserDto;
import com.epam.webchat.leonidivanov.datalayer.entity.User;
import com.epam.webchat.leonidivanov.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class describes controller for work with User entity
 */
@Slf4j
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
        log.debug("Getting ifo about all users");
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
        log.info("Getting info about user with ID {}", userId);
        return convertToDto(userService.getUserData(userId));
    }

    /**
     * Adds new user
     *
     * @param addingUser -- new user data
     * @return added user
     */
    @PostMapping("/register")
    public UserDto registerUser(@RequestBody @Valid UserDto addingUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("UserDto is not valid {}", addingUser);
            throw new ValidationException("User data is not valid");
        }
        log.debug("Register new user with login: {}", addingUser.getLogin());
        UserDto userDto = convertToDto(userService.register(convertToEntity(addingUser)));
        log.debug("New user was registered {}", userDto);
        return userDto;
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
        log.debug("Ban user with ID: {}", userId);
        UserDto user = convertToDto(userService.banUser(userId));
        log.info("User {} has been banned", user);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    /**
     * Unbans user
     *
     * @param userId --kicking user id
     */
    @Secured("ROLE_ADMINISTRATOR")
    @PostMapping("/unban/{userId}")
    public ResponseEntity<UserDto> unbanUser(@PathVariable Long userId) {
        log.debug("Unban user with ID: {}", userId);
        UserDto user = convertToDto(userService.unbanUser(userId));
        log.info("User {} has been banned", user);
        return new ResponseEntity<>(user, HttpStatus.OK);
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
