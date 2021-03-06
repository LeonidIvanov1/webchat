package com.epam.webchat.leonidivanov.security;

import com.epam.webchat.leonidivanov.datalayer.entity.User;
import com.epam.webchat.leonidivanov.security.jwt.JwtUser;
import com.epam.webchat.leonidivanov.security.jwt.JwtUserFactory;
import com.epam.webchat.leonidivanov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.findByUserLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User with login "
                    + login + "not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);

        return jwtUser;
    }


}
