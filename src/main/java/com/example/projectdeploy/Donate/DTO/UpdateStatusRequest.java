package com.example.projectdeploy.Donate.DTO;

import com.example.projectdeploy.Donate.Model.Status;
import com.example.projectdeploy.MedicalInformation.BloodPressure.dto.AM_PM;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Data
public class UpdateStatusRequest {
    UUID donateNotifiedId;
    UUID requstor=null;
    UUID donator=null;
    Status status;
    Date dateOfArrival=null;
    Time time=null;
    AM_PM am_pm;
}
