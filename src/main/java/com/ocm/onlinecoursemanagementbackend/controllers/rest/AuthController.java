package com.ocm.onlinecoursemanagementbackend.controllers.rest;


import com.ocm.onlinecoursemanagementbackend.config.security.CustomUserDetailsService;
import com.ocm.onlinecoursemanagementbackend.config.security.JWTService;
import com.ocm.onlinecoursemanagementbackend.dtos.auth.LoginRequestDTO;
import com.ocm.onlinecoursemanagementbackend.dtos.auth.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class AuthController {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateAndGenerateToken(@RequestBody LoginRequestDTO user) {
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            String token = jwtService.generateToken(result);
            return ResponseEntity.ok(new LoginResponseDTO("User logged in successfully", token, 200));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

}
