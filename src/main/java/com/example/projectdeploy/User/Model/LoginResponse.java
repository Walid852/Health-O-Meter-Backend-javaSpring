package com.example.projectdeploy.User.Model;

import java.util.UUID;

public class LoginResponse {
    UUID userId;
    String token;
    String role;

    public LoginResponse(UUID userId, String token,String role) {
        this.userId = userId;
        this.token = token;
        this.role=role;
    }
}
