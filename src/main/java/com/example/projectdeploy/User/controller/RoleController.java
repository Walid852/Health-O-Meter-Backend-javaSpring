package com.example.projectdeploy.User.controller;

import com.example.projectdeploy.User.Model.Role;
import com.example.projectdeploy.User.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping(value = "/addRole")
    public @ResponseBody
    Role addRole(@RequestParam String roleName){
        return roleService.addRole(roleName);
    }
}
