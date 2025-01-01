package com.ocm.onlinecoursemanagementbackend.config.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        jwtUtil.setSecret("my-secret-key");
        jwtUtil.setExpiration(3600000L); // 1 hour
    }

    @Test
    void generateToken() {
        UserDetails userDetails = new User("testUser", "testPassword", Collections.emptyList());
        String token = jwtUtil.generateToken(userDetails);
        assertNotNull(token);
    }

    @Test
    void extractUsername() {
        UserDetails userDetails = new User("testUser", "testPassword", Collections.emptyList());
        String token = jwtUtil.generateToken(userDetails);
        String username = jwtUtil.extractUsername(token);
        assertEquals("testUser", username);
    }

}