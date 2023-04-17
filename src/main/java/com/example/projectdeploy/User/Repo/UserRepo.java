package com.example.projectdeploy.User.Repo;


import com.example.projectdeploy.User.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//String refers to pr key
@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
    @Query("select C from User C where C.userName=?1")
    User findByUsername(String userName);

    @Query("select C from User C where C.email=?1")
    User findByEmail(String email);

    @Query("select C from User C where C.id=?1")
    User findByUserId(UUID userName);


}
