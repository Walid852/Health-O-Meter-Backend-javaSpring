package com.example.projectdeploy.User.controller;
import com.example.projectdeploy.User.dto.RegisterDto;
import com.example.projectdeploy.User.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userService/auth")
public class AuthController {
    @Autowired
    UserServices userServices;

    @PostMapping("register")

    @ResponseBody
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
     return userServices.register(registerDto);
    }
}