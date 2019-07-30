package com.epam.webchat.leonidivanov.security.jwt;

import com.epam.webchat.leonidivanov.datalayer.entity.User;
import com.epam.webchat.leonidivanov.datalayer.entity.enums.UserRole;
import com.epam.webchat.leonidivanov.datalayer.entity.enums.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getUserId(),
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getFullName(),
                user.getUserRole(),
                user.getUserStatus(),
                user.getUserStatus().equals(UserStatus.ONLINE),
                mapToGrantedAuthorities(user.getUserRole())
        );
    }

    private static GrantedAuthority mapToGrantedAuthorities(UserRole userRole) {
        return new SimpleGrantedAuthority(userRole.toString());
    }
}
