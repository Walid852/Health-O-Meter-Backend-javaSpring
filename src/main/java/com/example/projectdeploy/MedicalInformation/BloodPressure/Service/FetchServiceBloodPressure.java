package com.example.projectdeploy.MedicalInformation.BloodPressure.Service;

import com.example.projectdeploy.MedicalInformation.BloodPressure.Model.BloodPressure;
import com.example.projectdeploy.MedicalInformation.BloodPressure.Repo.BloodPressureRepo;
import com.example.projectdeploy.MedicalInformation.BloodPressure.dto.BloodPressureCategory;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FetchServiceBloodPressure {
    @Autowired
    BloodPressureRepo bloodPressureRepo;
    @Autowired
    MedicalInformationRepo medicalInformationRepo;

    public BloodPressure GetBloodPressureById(UUID id){
        return bloodPressureRepo.findBloodPressureById(id);
    }
    public List<BloodPressure> GetBloodPressureByMedicalInformationId(UUID MedicalInformationId){
        return bloodPressureRepo.findBloodPressureByMedicalInformationId(MedicalInformationId);
    }
    public List<BloodPressure> findBloodPressureDeleted(UUID MedicalInformationId){
        return bloodPressureRepo.findBloodPressureDeleted(MedicalInformationId);
    }
    public List<BloodPressure> GetAllBloodPressure(){
        return bloodPressureRepo.findAllBloodPressure();
    }
    public List<BloodPressure> GetAllBloodPressureByDate(Date start, Date endDate){
        return bloodPressureRepo.findAllBloodPressureByDate(start,endDate);
    }
    public List<BloodPressure> GetBloodPressureByDiastolic(int diastolic){
        return bloodPressureRepo.findBloodPressureByDiastolic(diastolic);
    }
    public List<BloodPressure> GetBloodPressureBySystolic(int systolic){
        return bloodPressureRepo.findBloodPressureBySystolic(systolic);
    }
    public List<BloodPressure> GetBloodPressureByBloodPressureCategory( BloodPressureCategory bloodPressureCategory){

        return bloodPressureRepo.findBloodPressureByBloodPressureCategory(bloodPressureCategory);
    }
}
