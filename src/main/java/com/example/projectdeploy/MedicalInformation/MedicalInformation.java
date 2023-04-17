package com.example.projectdeploy.MedicalInformation;
import com.example.projectdeploy.Member.Model.Member;
import com.example.projectdeploy.User.Model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MedicalInformation implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    @Autowired
    @OneToOne(cascade = {CascadeType.ALL})
    private User user;
    int hemoglobin;
    Date lastTimeDonate;
    boolean isFever=false;
    Boolean haveAids=false;
    Boolean hepatitis_B=false;
    Boolean hepatitis_C=false;
    Boolean haveMalaria=false;
    Boolean haveSyphilis=false;
    Boolean haveSevereAnemia=false;
    Boolean haveCancer=false;
    Boolean haveDiabetes=false;
    Boolean haveHighBloodPressure=false;
    Boolean haveGeneticBloodDiseases=false;
    Boolean haveAbilityToDonate=false;
    private int numOfCubs;

    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    @Autowired
    @OneToOne
    private Member member;

    private double CurrentWeight;

    private double CurrentHeight;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
