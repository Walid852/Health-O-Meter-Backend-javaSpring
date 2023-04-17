package com.example.projectdeploy.User.security;


import com.example.projectdeploy.User.Model.Role;
import com.example.projectdeploy.User.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.projectdeploy.User.Model.User user=userRepo.findByUserId(UUID.fromString(username));

        if (user != null) {
            return new User(user.getId().toString(), user.getPassword(),
                    RolesToAutherities(user.getRoles()));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }


    private Collection<GrantedAuthority> RolesToAutherities(Role roles){
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>(); // use list if you wish
        String ROLE_PREFIX = "ROLE_";
        grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + roles.getName()));
        return grantedAuthorities;
    }
}
