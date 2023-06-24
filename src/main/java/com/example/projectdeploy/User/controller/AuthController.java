package com.example.projectdeploy.User.controller;
import com.example.projectdeploy.User.dto.RegisterDto;
import com.example.projectdeploy.User.security.JwtTokenUtil;
import com.example.projectdeploy.User.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/userService/auth")
public class AuthController {
    @Autowired
    UserServices userServices;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("register")

    @ResponseBody
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
     return userServices.register(registerDto);
    }

    @GetMapping("expired")
    @ResponseBody
    public void expired(@RequestParam String token) {
        if(jwtTokenUtil.isTokenExpired(token)){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "session is expired");
        }else
            throw new ResponseStatusException(HttpStatus.OK, "Okay");

    }
}