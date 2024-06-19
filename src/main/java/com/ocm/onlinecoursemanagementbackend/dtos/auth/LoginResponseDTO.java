package com.ocm.onlinecoursemanagementbackend.dtos.auth;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private String message;
    private int statusCode;


    public LoginResponseDTO(String userLoggedInSuccessfully, String token,
                                int statusCode) {
        this.message = userLoggedInSuccessfully;
        this.token = token;
        this.statusCode = 200;
    }
}
