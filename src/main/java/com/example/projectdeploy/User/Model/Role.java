package com.example.projectdeploy.User.Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import static jakarta.persistence.GenerationType.AUTO;


@Entity
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = AUTO)
    private UUID id;

    private String name;


}
