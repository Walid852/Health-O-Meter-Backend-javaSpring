package com.example.projectdeploy.Disease.Services;

import com.example.projectdeploy.Disease.Models.Medicine;
import com.example.projectdeploy.Disease.Repos.MedicineRepo;
import com.example.projectdeploy.MedicalInformation.Allergic.Model.Allergy;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FetchMedicalService {
    @Autowired
    MedicineRepo medicineRepo;
    @Transactional
    public Response<Medicine> findMedicineById(UUID id){
        try {
            List<Medicine> result = null;
            Medicine medicine=medicineRepo.findMedicineById(id);
            if(medicine!=null) {
                result.add(medicine);
            }
            else return new Response<>(false, StaticsText.MessageForTest("medicine", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("medicine", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Medicine> findMedicineByNameForMedicalInformationId(UUID id, String name){

        try {
            List<Medicine> result=medicineRepo.findMedicineByNameForMedicalInformationId(id,name);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("medicines", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("medicines", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<Medicine> findMedicineForMedicalInformationId(UUID id){
        try {
            List<Medicine> result=medicineRepo.findMedicineForMedicalInformationId(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("medicines", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("medicines", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Medicine> findMedicineForDisease(UUID id){
        try {
            List<Medicine> result=medicineRepo.findMedicineForDisease(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("medicines", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("medicines", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Medicine> findMedicineByIsNotifiedForMedicalInformationId(UUID id,Boolean isNotified){

        try {
            List<Medicine> result= medicineRepo.findMedicineByIsNotifiedForMedicalInformationId(id,isNotified);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("medicines", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("medicines", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

}
