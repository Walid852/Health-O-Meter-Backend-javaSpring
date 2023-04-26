package com.example.projectdeploy.MedicalInformation.BloodPressure.Service;

import com.example.projectdeploy.MedicalInformation.BloodPressure.Model.BloodPressure;
import com.example.projectdeploy.MedicalInformation.BloodPressure.Repo.BloodPressureRepo;
import com.example.projectdeploy.MedicalInformation.BloodPressure.dto.BloodPressureCategory;
import com.example.projectdeploy.MedicalInformation.BloodPressure.dto.CreateBloodPressure;
import com.example.projectdeploy.MedicalInformation.BloodPressure.dto.UpdateBloodPressure;
import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.Model.SugarBloodTest;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CrudServiceBloodPressure {
    @Autowired
    BloodPressureRepo bloodPressureRepo;
    @Autowired
    MedicalInformationRepo medicalInformationRepo;
    @Transactional
    public BloodPressureCategory CalculateCategory(int Systolic,int Diastolic){
        if(Systolic<120&&Diastolic<80){
            return BloodPressureCategory.NORMAL;
        }
        else if((Systolic>=120&&Systolic<=129)&&Diastolic<80){
            return BloodPressureCategory.ELEVATED;

        }else if ((Systolic>=130&&Systolic<=139)||
                (Diastolic>=80&&Diastolic<=89)){
            return BloodPressureCategory.HIGH_BLOOD_PRESSURE_STAGE_1;

        }else if ((Systolic>=140&&Systolic<=180)||
                (Diastolic>=90&&Diastolic<=120)){
            return BloodPressureCategory.HIGH_BLOOD_PRESSURE_STAGE_2;
        }else if(Systolic>181||Diastolic>120){
            return BloodPressureCategory.HYPERTENSIVE_CRISIS;
        }else {
            return null;
        }
    }
    public Response<BloodPressure> AddBloodPressure(CreateBloodPressure createBloodPressure){
        try{
            BloodPressure bloodPressure=new BloodPressure();
            MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(createBloodPressure.getMedicalInfoId());
            bloodPressure.setMedicalInformation(medicalInformation);
            if(createBloodPressure.getSystolic()==-1||createBloodPressure.getDiastolic()==-1)return null;
            bloodPressure.setSystolic(createBloodPressure.getSystolic());
            bloodPressure.setDiastolic(createBloodPressure.getDiastolic());
            bloodPressure.setBloodPressureCategory(CalculateCategory(createBloodPressure.getSystolic(),createBloodPressure.getDiastolic()));
            bloodPressureRepo.save(bloodPressure);
            List<BloodPressure> result = new ArrayList<>();
            result.add(bloodPressure);
            return new Response<>(true, StaticsText.MessageForTest("Blood Pressure Test", "added"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    public Response<BloodPressure>  UpdateBloodPressure(UpdateBloodPressure updateBloodPressure){
        try{
            BloodPressure bloodPressure=bloodPressureRepo.findBloodPressureById(updateBloodPressure.getBloodPressureId());
            if(updateBloodPressure.getSystolic()!=-1)bloodPressure.setSystolic(updateBloodPressure.getSystolic());
            if(updateBloodPressure.getDiastolic()!=-1)bloodPressure.setDiastolic(updateBloodPressure.getDiastolic());
            bloodPressure.setBloodPressureCategory(CalculateCategory(bloodPressure.getSystolic(),bloodPressure.getDiastolic()));
            bloodPressureRepo.save(bloodPressure);
            List<BloodPressure> result = new ArrayList<>();
            result.add(bloodPressure);
            return new Response<>(true, StaticsText.MessageForTest("Blood Pressure Test", "added"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    public Response<BloodPressure>  DeleteBloodPressure(UUID bloodPressureId){
        try{
            BloodPressure bloodPressure=bloodPressureRepo.findBloodPressureById(bloodPressureId);
            if(bloodPressure==null)return new Response<>(false, StaticsText.MessageForTest("Blood Pressure Test", "not found"));
            bloodPressure.setDeleted(true);
            bloodPressureRepo.save(bloodPressure);
            List<BloodPressure> result = new ArrayList<>();
            result.add(bloodPressure);
            return new Response<>(true, StaticsText.MessageForTest("Blood Pressure Test", "added"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

}
