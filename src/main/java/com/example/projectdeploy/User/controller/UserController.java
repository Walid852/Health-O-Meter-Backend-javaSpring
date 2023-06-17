package com.example.projectdeploy.User.controller;

import com.example.projectdeploy.User.Gender;
import com.example.projectdeploy.User.Model.User;
import com.example.projectdeploy.User.dto.ChangePasswordDto;
import com.example.projectdeploy.User.dto.RegisterDto;
import com.example.projectdeploy.User.service.UserServices;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    UserServices userServices;

    @GetMapping(value = "/getMyInfo")
    @RolesAllowed({"ROLE_User"})
    public @ResponseBody
    User getUserInfo(@RequestParam UUID userId){
        return userServices.getUserInfo(userId);
    }

    @GetMapping(value = "/all")
    public @ResponseBody List<User> getAllUsers(){
        return userServices.getAllUsers();
    }

    @GetMapping(value = "/getGenderByUserId")
    public @ResponseBody com.example.projectdeploy.Shared.Response<Gender> getGenderByUserId(UUID userId){
        return userServices.getGenderByUserId(userId);
    }

    @PutMapping(value = "/updateUser")
    public @ResponseBody User updateUserInfo(@RequestBody RegisterDto registerDto){
        return userServices.updateUserInfo(registerDto);
    }
    @PutMapping(value = "/ChangePassword")
    public @ResponseBody
    ResponseEntity<?> ChangePassword(@RequestBody ChangePasswordDto changePasswordDto){
        return userServices.ChangePassword(changePasswordDto);
    }

    @DeleteMapping(value = "/deletUser")
    public @ResponseBody User deleteUser(@RequestBody RegisterDto registerDto){
        return userServices.deleteUser(registerDto);
    }


}
