package com.epam.webchat.leonidivanov.controller;

import com.epam.webchat.leonidivanov.controller.dto.AuthenticationRequestDto;
import com.epam.webchat.leonidivanov.controller.dto.AuthenticationResponseDto;
import com.epam.webchat.leonidivanov.datalayer.entity.User;
import com.epam.webchat.leonidivanov.security.jwt.JwtTokenProvider;
import com.epam.webchat.leonidivanov.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * Controller for processing request for authorization, login and logout
 */
@Slf4j
@RestController
@RequestMapping(value = "/auth/")
public class AuthenticationRestController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }


    /**
     * Processing login request
     *
     * @param requestDto -- data for login user
     * @return JSON with info about username and JWT token
     */
    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            User user = userService.findByUserLogin(requestDto.getUsername());
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " +
                        requestDto.getUsername() + " not found");
            }
            log.debug("Trying to authenticate with username: {}", user.getLogin());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getLogin(), requestDto.getPassword()));
            log.debug("Authenticate for user {} was successful", user.getLogin());
            log.debug("Generated token for user {}", user.getLogin());
            String token = jwtTokenProvider.createToken(user.getLogin());
            userService.login(user);
            log.info("Token for user {} was generated ", user.getLogin());
            AuthenticationResponseDto response = new AuthenticationResponseDto();
            response.setUsername(user.getLogin());
            response.setUserID(user.getUserId());
            response.setToken(token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            log.error("Error in user: {} authentication", requestDto.getUsername(), e);
            throw new BadCredentialsException("Invalid username or password", e);
        }
    }

    /**
     * Processing logout request
     *
     * @param request --logout HTTP request
     * @return status OK
     */
    @Secured({"ROLE_ADMINISTRATOR", "ROLE_USER"})
    @PostMapping("logout")
    public ResponseEntity logout(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        User user = userService.findByUserLogin(jwtTokenProvider.getUserLogin(token));
        log.debug("User {} trying to logout", user.getLogin());
        userService.logout(user);
        log.info("User {} logged out", user.getLogin());
        return new ResponseEntity(HttpStatus.OK);
    }

}
