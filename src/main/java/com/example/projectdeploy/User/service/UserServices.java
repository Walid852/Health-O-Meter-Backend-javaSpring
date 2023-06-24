package com.example.projectdeploy.User.service;
import com.example.projectdeploy.Map.Model.UserLocation;
import com.example.projectdeploy.Map.Service.LocationService;
import com.example.projectdeploy.User.Model.SaveRegistrationTokenRequest;
import com.example.projectdeploy.Shared.StaticsText;
import com.example.projectdeploy.User.Gender;
import com.example.projectdeploy.User.Model.Response;
import com.example.projectdeploy.User.Model.Role;
import com.example.projectdeploy.User.Model.User;
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

import java.util.LinkedList;
import java.util.List;
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
    @Autowired
    LocationService locationService;
    public void SaveRegistrationToken(SaveRegistrationTokenRequest saveRegistrationTokenRequest){
        User user=userRepo.findByUserId(saveRegistrationTokenRequest.getUserId());
        user.setRegistrationToken(saveRegistrationTokenRequest.getRegistrationToken());
        userRepo.save(user);
    }
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
            Role roles = roleRepo.findByName(registerDto.getRole());
            user.setRoles(roles);
            user.setAge(registerDto.getAge());
            user.setEmail(registerDto.getEmail());
            user.setGender(registerDto.getGender());
            user.setNationalId(registerDto.getNationalId());
            user.setPhoneNumber(registerDto.getPhoneNumber());
            user.setName(registerDto.getName());
            //UserLocation userLocation=locationService.SaveLocation(registerDto.getLating());
            //user.setLocation(userLocation);
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
    public ResponseEntity<?> UpdateLocation(String Lating,UUID userId){
        UserLocation userLocation=locationService.SaveLocation(Lating);
        User user=userRepo.findByUserId(userId);
        user.setLocation(userLocation);
        userRepo.save(user);
        return ResponseEntity.ok("Successfully add Location");
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
    public com.example.projectdeploy.Shared.Response<Gender> getGenderByUserId(UUID userId){
        try{
            List<Gender> genders=new LinkedList<>();
            genders.add(userRepo.findByUserId(userId).getGender());
        return new com.example.projectdeploy.Shared.Response<>(true, StaticsText.MessageForTest("Donate ", "Created"), genders);
    }catch (Exception e){
        return new com.example.projectdeploy.Shared.Response<>(false, StaticsText.MessageForTestError());
    }
    }


}
