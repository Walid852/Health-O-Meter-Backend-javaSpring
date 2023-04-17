package com.example.projectdeploy.User.service;
import com.example.projectdeploy.User.Model.Role;
import com.example.projectdeploy.User.Model.User;
import com.example.projectdeploy.User.Model.res;
import com.example.projectdeploy.User.Repo.RoleRepo;
import com.example.projectdeploy.User.Repo.UserRepo;
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
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    public ResponseEntity<?> register(RegisterDto registerDto) {
        if (userRepo.findByUsername(registerDto.getUsername())!=null ) {
            return ResponseEntity.badRequest().body(new res("error","userName is taken!"));
        }
        if (userRepo.findByEmail(registerDto.getEmail())!=null ) {
            return ResponseEntity.badRequest().body(new res("error","You have already have an email do you forget password?"));
        }
        User user = new User();
        if(registerDto.getMemberId()!=null)
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
        return ResponseEntity.ok(user);
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