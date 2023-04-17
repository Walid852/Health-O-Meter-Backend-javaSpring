package com.example.projectdeploy.Member.Model;


import com.example.projectdeploy.Member.Type;
import com.example.projectdeploy.User.Gender;
import com.example.projectdeploy.User.Model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = EAGER,cascade = CascadeType.ALL)
    User user;

    @Enumerated(EnumType.STRING)
    Type typeOfMember;

    int age;

    String name;

    Date birthDate;

    @Enumerated(EnumType.STRING)
    Gender gender;
}
