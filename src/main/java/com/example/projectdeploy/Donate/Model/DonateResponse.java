package com.example.projectdeploy.Donate.Model;

import com.example.projectdeploy.MedicalInformation.BloodType;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data
public class DonateResponse {
    UUID id;
    Date donateDate;
    boolean isDone;
    BloodType bloodType;
    double lat;
    double lng;
    LocationHierarchical current;
    UUID requestorMedicalInformationId;
    UUID donatorMedicalInformationId;

    public DonateResponse(UUID id, Date donateDate, boolean isDone, BloodType bloodType, double lat, double lng, LocationHierarchical current, UUID requestorMedicalInformationId, UUID donatorMedicalInformationId) {
        this.id = id;
        this.donateDate = donateDate;
        this.isDone = isDone;
        this.bloodType = bloodType;
        this.lat = lat;
        this.lng = lng;
        this.current = current;
        this.requestorMedicalInformationId = requestorMedicalInformationId;
        this.donatorMedicalInformationId = donatorMedicalInformationId;
    }
}
