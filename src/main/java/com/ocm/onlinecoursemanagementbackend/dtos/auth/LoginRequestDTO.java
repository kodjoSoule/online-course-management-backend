package com.ocm.onlinecoursemanagementbackend.dtos.auth;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;

    private String username;

    public LoginRequestDTO() {
    }

    public LoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public LoginRequestDTO(String jwt) {
        this.username = jwt;
    }
}
