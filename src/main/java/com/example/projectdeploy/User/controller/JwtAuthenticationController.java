package com.example.projectdeploy.User.controller;

import com.example.projectdeploy.User.Model.LoginResponse;
import com.example.projectdeploy.User.Model.Response;
import com.example.projectdeploy.User.Model.User;
import com.example.projectdeploy.User.Model.res;
import com.example.projectdeploy.User.Repo.UserRepo;
import com.example.projectdeploy.User.dto.JwtRequest;
import com.example.projectdeploy.User.security.JwtTokenUtil;
import com.example.projectdeploy.User.security.JwtUserDetailsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserRepo userRepo;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        User user=userRepo.findByUsername(authenticationRequest.getUsername());
        if(user!=null)
        authenticate(user.getId().toString(), authenticationRequest.getPassword());
        else
            return ResponseEntity.badRequest().body(new res("error","User not found"));

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getId().toString());

        final String token = jwtTokenUtil.generateToken(userDetails);
        Response response=new Response(true, "Sucess Login in",new LoginResponse(user.getId(),token,user.getRoles().getName()));

        return ResponseEntity.ok().body(gson.toJson(response));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    @RequestMapping(value = "/temp", method = RequestMethod.GET)
    public ResponseEntity<?> temp() throws Exception {
        System.out.println("HI");
        return ResponseEntity.ok().body("Walid say hello world");
    }

}
