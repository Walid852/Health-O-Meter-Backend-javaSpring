package com.example.projectdeploy.Test.Requests;

import java.sql.Date;
import java.util.UUID;

public class LiverFunctionRequest {
    private UUID id;
    UUID medicalInformation;
    private boolean isDeleted=false;
    private Date date;
    float GammaGT;
    float Bilirubin_Total;
    float Bilirubin_Direct;
    float AST;
    float ALT;
    float Alk;
    float TotalProtein;
    float Albumin;
}
