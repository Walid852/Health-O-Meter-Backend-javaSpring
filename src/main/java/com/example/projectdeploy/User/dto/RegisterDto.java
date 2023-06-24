package com.example.projectdeploy.User.dto;
import com.example.projectdeploy.User.Gender;
import lombok.Data;

import java.util.UUID;

@Data
public class RegisterDto {
    private UUID uid;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String name;
    private int age;
    private Gender gender;
    private String nationalId;
    private String role;
    private UUID memberId;
    //private String Lating;
}
