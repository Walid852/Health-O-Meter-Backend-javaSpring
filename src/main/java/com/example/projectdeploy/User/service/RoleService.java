package com.example.projectdeploy.User.service;


import com.example.projectdeploy.User.Model.Role;
import com.example.projectdeploy.User.Repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepo roleRepo;

    public Role addRole(String roleName){
        Role role= new Role();
        role.setName(roleName);
        roleRepo.save(role);
        return role;
    }
}
