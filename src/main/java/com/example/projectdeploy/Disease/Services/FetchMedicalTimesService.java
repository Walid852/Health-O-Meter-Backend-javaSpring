package com.example.projectdeploy.Disease.Services;

import com.example.projectdeploy.Disease.Models.MedicineTime;
import com.example.projectdeploy.Disease.Repos.MedicineTimeRepo;
import com.example.projectdeploy.MedicalInformation.Allergic.Model.Allergy;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FetchMedicalTimesService {
    @Autowired
    MedicineTimeRepo medicineTimeRepo;
    public Response<MedicineTime>  findMedicineTimeById(UUID id){
        try {
            List<MedicineTime> result=null;
            MedicineTime medicineTime=medicineTimeRepo.findMedicineTimeById(id);
            if(medicineTime!=null) {
                result.add(medicineTime);
            }
            else return new Response<>(false, StaticsText.MessageForTest("medicineTime", "not Found"));

            return new Response<>(true, StaticsText.MessageForTest("MedicineTime", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    public Response<MedicineTime>  findMedicineTimeByMedicineId(UUID id){

        try {
            List<MedicineTime> result=medicineTimeRepo.findMedicineTimeByMedicineId(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("MedicineTime", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("MedicineTime", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    public Response<MedicineTime>  findMedicineTimeByDiseaseId(UUID id){
        try {
            List<MedicineTime> result=medicineTimeRepo.findMedicineTimeByDiseaseId(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("MedicineTime", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("MedicineTime", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    public Response<MedicineTime>  findMedicineTimeByMedicalInformationIdAndNotified(UUID id){
        try {
            List<MedicineTime> result=medicineTimeRepo.findMedicineTimeByMedicalInformationIdAndNotified(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("MedicineTime", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("MedicineTime", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
}
