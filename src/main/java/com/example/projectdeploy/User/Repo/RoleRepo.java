package com.example.projectdeploy.User.Repo;


import com.example.projectdeploy.User.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepo extends JpaRepository<Role, UUID> {

    Role findByName(String RoleName);

}
