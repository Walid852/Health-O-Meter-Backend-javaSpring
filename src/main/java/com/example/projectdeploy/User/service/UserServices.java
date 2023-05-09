package com.example.projectdeploy.User.service;
import com.example.projectdeploy.User.Model.Response;
import com.example.projectdeploy.User.Model.Role;
import com.example.projectdeploy.User.Model.User;
import com.example.projectdeploy.User.Model.res;
import com.example.projectdeploy.User.Repo.RoleRepo;
import com.example.projectdeploy.User.Repo.UserRepo;
import com.example.projectdeploy.User.controller.JwtAuthenticationController;
import com.example.projectdeploy.User.dto.ChangePasswordDto;
import com.example.projectdeploy.User.dto.JwtRequest;
import com.example.projectdeploy.User.dto.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServices {

    @Autowired
    private JwtAuthenticationController jwtAuthenticationController;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    public ResponseEntity<?> register(RegisterDto registerDto) {
        try {
            if (userRepo.findByUsername(registerDto.getUsername()) != null) {
                return ResponseEntity.badRequest().body(new Response(false, "userName is taken!"));
            }
            if (userRepo.findByEmail(registerDto.getEmail()) != null) {
                return ResponseEntity.badRequest().body(new Response(false, "You have already have an email do you forget password?"));
            }
            User user = new User();
            if (registerDto.getMemberId() != null)
                user.setId(registerDto.getMemberId());
            user.setUserName(registerDto.getUsername());
            user.setPassword(bcryptEncoder.encode((registerDto.getPassword())));
            //all is user at first
            Role roles = roleRepo.findByName("User");
            user.setRoles(roles);
            user.setAge(registerDto.getAge());
            user.setEmail(registerDto.getEmail());
            user.setGender(registerDto.getGender());
            user.setNationalId(registerDto.getNationalId());
            user.setPhoneNumber(registerDto.getPhoneNumber());
            user.setName(registerDto.getName());
            userRepo.save(user);
            Response response=jwtAuthenticationController.createAuthenticationToken(new JwtRequest(registerDto.getUsername(),registerDto.getPassword()));
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new Response(false, "Something wrong Make Ensure do Correct process and try agin"));
        }
    }
    public ResponseEntity<?> ChangePassword(ChangePasswordDto changePasswordDto) {
        try {
            User user=userRepo.findByUserId(changePasswordDto.getUid());
            if (user== null) {
                return ResponseEntity.badRequest().body(new Response(false, "user not found"));
            }
            if (user.getPassword().equals(bcryptEncoder.encode((changePasswordDto.getOldPassword())))){
                user.setPassword(bcryptEncoder.encode((changePasswordDto.getNewPassword())));
            }
            else {
                return ResponseEntity.badRequest().body(new Response(false, "old Password is wrong"));
            }
            userRepo.save(user);
            return ResponseEntity.accepted().body(new Response(true, "Password changed"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new Response(false, "Something wrong Make Ensure do Correct process and try agin"));
        }
    }


    public User getUserInfo(UUID userId){
        if(userRepo.findById(userId).isPresent())
            return userRepo.findById(userId).get();
        return null;
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User updateUserInfo(RegisterDto registerDto){
        User updatedUser=new User();
        if(userRepo.findById(registerDto.getUid()).isPresent()){
            updatedUser=userRepo.findById(registerDto.getUid()).get();

        if(registerDto.getEmail()!=null)
            updatedUser.setEmail(registerDto.getEmail());

        if(registerDto.getPhoneNumber()!=null)
            updatedUser.setPhoneNumber(registerDto.getPhoneNumber());

        if(registerDto.getName()!=null)
            updatedUser.setName(registerDto.getName());

        if(registerDto.getAge()!=0)
            updatedUser.setAge(registerDto.getAge());
        userRepo.save(updatedUser);
        }
        return updatedUser;
    }

    public User deleteUser(RegisterDto registerDto){
        User deletedUser =new User();
        if(userRepo.findById(registerDto.getUid()).isPresent()){
            deletedUser =userRepo.findById(registerDto.getUid()).get();
            userRepo.delete(deletedUser);
        }
        return deletedUser;
    }



}
