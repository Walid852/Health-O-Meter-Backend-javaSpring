package com.example.projectdeploy.Test.Requests;

import java.sql.Date;
import java.util.UUID;

public class UrineRequest {
    UUID id;
    UUID medicalInformationId;
    boolean isDeleted=false;
    Date date;
    String Color;
    String Clarity;
    float SpecificGravity;
    boolean UrinePH;
    boolean Protein;
    boolean Glucose;
    boolean Ketone;
    boolean Urinebilirubin;
    boolean Nitrite;
    boolean Crystals;
    boolean Casts;
}
