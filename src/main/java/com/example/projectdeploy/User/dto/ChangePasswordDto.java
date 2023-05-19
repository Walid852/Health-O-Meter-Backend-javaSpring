package com.example.projectdeploy.User.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class ChangePasswordDto {
    private UUID uid;
    private String oldPassword;
    private String newPassword;
}
