package com.example.projectdeploy.User.Model;

import lombok.Data;

import java.util.UUID;
@Data
public class SaveRegistrationTokenRequest {
    UUID userId;
    String registrationToken;
}
