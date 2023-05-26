package com.example.projectdeploy.MedicalInformation.Allergic.Service;

import com.example.projectdeploy.MedicalInformation.Allergic.Model.Allergy;
import com.example.projectdeploy.MedicalInformation.Allergic.Repo.AllergyRepo;
import com.example.projectdeploy.MedicalInformation.Allergic.Request.AllergyRequest;
import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import com.example.projectdeploy.Test.Models.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AllergyService {

    @Autowired
    AllergyRepo allergyRepo;

    @Autowired
    MedicalInformationRepo medicalInformationRepo;
    @Transactional
    public Response<Allergy>  addAllergy(AllergyRequest allergyRequest){
        try {
            Allergy allergy =new Allergy();
            MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(allergyRequest.getMedicalInformationId());
            if(medicalInformation==null||allergyRequest.getName()==null)return new Response<>(false, StaticsText.MessageForTest("error", "not have a name"));
            allergy.setDate(allergyRequest.getDate());
            allergy.setName(allergyRequest.getName());
            allergy.setMedicalInformation(medicalInformation);
            allergyRepo.save(allergy);
            List<Allergy> result = new ArrayList<>();
            result.add(allergy);
            return new Response<>(true, StaticsText.MessageForTest("Allergy", "Created"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
        }
    @Transactional
    public Response<Allergy>  updateAllergy(AllergyRequest allergyRequest){
        try {
            Allergy updatedAllergy =allergyRepo.getAllergyById(allergyRequest.getAllergyId());
            if(updatedAllergy==null)return new Response<>(false, StaticsText.MessageForTest("error", "not have this allergic"));
            if(allergyRequest.getName()!=null)updatedAllergy.setName(allergyRequest.getName());
            if(allergyRequest.getDate()!=null)updatedAllergy.setDate(allergyRequest.getDate());
            allergyRepo.save(updatedAllergy);
            List<Allergy> result = new ArrayList<>();
            result.add(updatedAllergy);
            return new Response<>(true, StaticsText.MessageForTest("Allergy", "Updated"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<Allergy> deleteAllergy(UUID id){
        try {
            Allergy deletedAllergy =new Allergy();
            if(allergyRepo.findById(id).isPresent()){
                deletedAllergy =allergyRepo.findById(id).get();
            }
            deletedAllergy.setDeleted(true);
            allergyRepo.save(deletedAllergy);
            List<Allergy> result = new ArrayList<>();
            result.add(deletedAllergy);
            return new Response<>(true, StaticsText.MessageForTest("Allergy", "Deleted"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Allergy> getAllergyByMedicalInformationId(UUID medicalInformationId){
        try {
            List<Allergy> result=allergyRepo.getAllergyByMedicalInformationId(medicalInformationId);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Allergies", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Allergies", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<Allergy> getAllergyDeletedByMedicalInformationId(UUID medicalInformationId){
        try {
            List<Allergy> result=allergyRepo.getAllergyDeletedByMedicalInformationId(medicalInformationId);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Allergies", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Allergies", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Allergy> getAllAllergy(){
        try {
            List<Allergy> result=allergyRepo.getAllAllergy();
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Allergies", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Allergies", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Allergy> getAllergyByName(String name){
        try {
            List<Allergy> result=allergyRepo.getAllergyByName(name);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Allergies", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Allergies", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Allergy> getAllergyByDate(Date date){
        try {
            List<Allergy> result=allergyRepo.getAllergyByDate(date);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Allergies", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Allergies", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Allergy> getAllergyById(UUID id){
        try {
            List<Allergy> result = null;
            Allergy allergy=allergyRepo.getAllergyById(id);
            if(allergy!=null) {
                result.add(allergy);
            }
            else return new Response<>(false, StaticsText.MessageForTest("Allergies", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Allergies", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
}
