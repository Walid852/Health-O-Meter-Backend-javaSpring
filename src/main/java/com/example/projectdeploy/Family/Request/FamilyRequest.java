package com.example.projectdeploy.Family.Request;

import com.example.projectdeploy.Family.Relationship;
import lombok.Data;

import java.util.UUID;

@Data
public class FamilyRequest {
    UUID familyId;
    UUID first;
    String second;
    Relationship relationship;
}
