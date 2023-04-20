package com.example.projectdeploy.Family.Model;

import com.example.projectdeploy.Family.Relationship;
import com.example.projectdeploy.User.Model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Data
public class Family {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = EAGER,cascade = CascadeType.ALL)
    User first;

    @ManyToOne(fetch = EAGER,cascade = CascadeType.ALL)
    User Second;

    boolean isApproved = false;

    @Enumerated(EnumType.STRING)
    Relationship relationship;

}
