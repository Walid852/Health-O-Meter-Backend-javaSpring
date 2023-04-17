package com.example.projectdeploy.User.Model;

import com.example.projectdeploy.Map.Model.UserLocation;
import com.example.projectdeploy.User.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.UUID;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;
    @Column(unique = true)
    private String userName;
    private String email;
    private String phoneNumber;
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @ManyToOne(fetch = EAGER,cascade = CascadeType.ALL)
    private Role roles ;
    private int nationalId;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private UserLocation location ;

    //private listOfDiseases;
    //private listOfWaterInfo;
    //private listOfMember;
    //private listOfTests;

}
