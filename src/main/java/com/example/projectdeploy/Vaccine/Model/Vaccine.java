package com.example.projectdeploy.Vaccine.Model;


import com.example.projectdeploy.Member.Model.Member;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Data
public class Vaccine {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    String name;

    @ManyToOne(fetch = EAGER,cascade = CascadeType.ALL)
    Member member;

    Date recommendedVaccineDate;

    Date actualVaccineDate;

    boolean isTaken=false;


}
