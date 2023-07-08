package com.example.projectdeploy.Member.Request;

import com.example.projectdeploy.MedicalInformation.BloodType;
import com.example.projectdeploy.Member.Type;
import com.example.projectdeploy.User.Gender;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
public class MemberRequest {
    UUID memberId;
    String name;
    Gender gender;
    Type type;
    int age;
    UUID userId;
    Date birthDate;
    BloodType bloodType;
    double currentWeight;
    double currentHeight;
}
