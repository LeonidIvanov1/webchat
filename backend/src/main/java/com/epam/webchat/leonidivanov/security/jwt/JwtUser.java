package com.epam.webchat.leonidivanov.security.jwt;

import com.epam.webchat.leonidivanov.datalayer.entity.enums.UserRole;
import com.epam.webchat.leonidivanov.datalayer.entity.enums.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class JwtUser implements UserDetails {
    private final long userId;
    private final String login;
    private final String password;
    private final String email;
    private final String fullName;
    private final UserRole userRole;
    private final UserStatus userStatus;
    private final boolean enabled;
    private final GrantedAuthority authorities;

    public JwtUser(
            long userId,
            String login,
            String password,
            String email,
            String fullName,
            UserRole userRole,
            UserStatus userStatus,
            boolean enabled,
            GrantedAuthority authorities) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.userRole = userRole;
        this.userStatus = userStatus;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(authorities);
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !UserStatus.BANNED.equals(userStatus);
    }
}
