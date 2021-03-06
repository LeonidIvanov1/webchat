package com.epam.webchat.leonidivanov.security.jwt;

import com.epam.webchat.leonidivanov.datalayer.entity.enums.UserRole;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class JwtTokenProvider {
    public static final String AUTHORIZATION_HEADER_NAME = "authorization";
    public static final String BEARER_TOKEN_START = "Bearer_";

    private static String secret = "webchat";
    private static final Long VALIDITY_MILLISECONDS = 3600000L;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTHORIZATION_HEADER_NAME);
        if (bearerToken != null && bearerToken.startsWith(BEARER_TOKEN_START)) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public String createToken(String login) {
        Claims claims = Jwts.claims().setSubject(login);
        claims.put("roles", getRoleNames());

        Date now = new Date();
        Date validity = new Date(now.getTime() + VALIDITY_MILLISECONDS);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secret)//
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUserLogin(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserLogin(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());

        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }

    private List<String> getRoleNames() {
        List<String> roleNames = new ArrayList<>();
        for (UserRole userRole : UserRole.values()) {
            roleNames.add(userRole.toString());
        }
        return roleNames;
    }
}
