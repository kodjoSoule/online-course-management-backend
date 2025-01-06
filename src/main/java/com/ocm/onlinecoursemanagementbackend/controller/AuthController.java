package com.ocm.onlinecoursemanagementbackend.controller;

import com.ocm.onlinecoursemanagementbackend.config.JwtTokenProvider;
import com.ocm.onlinecoursemanagementbackend.model.User;
import com.ocm.onlinecoursemanagementbackend.model.UserDTO;
import com.ocm.onlinecoursemanagementbackend.model.UserRequest;
import com.ocm.onlinecoursemanagementbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRequest user) {
        log.info("Registering user: {}", user);
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        String token = userService.authenticate(username, password);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
